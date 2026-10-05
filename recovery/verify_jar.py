"""Verify recovered archive resources, mixin classes, and original SRG members.

Usage: python recovery/verify_jar.py original.jar build/libs/recovered.jar
No third-party Python dependencies required.
"""
import hashlib
import json
import struct
import sys
import zipfile

def members(data):
    pos = 8
    def u2():
        nonlocal pos
        value = struct.unpack_from('>H', data, pos)[0]
        pos += 2
        return value
    cp = [None] * u2()
    i = 1
    while i < len(cp):
        tag = data[pos]; pos += 1
        if tag == 1:
            size = u2(); cp[i] = data[pos:pos+size].decode('utf-8', errors='replace'); pos += size
        elif tag in (3, 4): pos += 4
        elif tag in (5, 6): pos += 8; i += 1
        elif tag in (7, 8, 16, 19, 20): pos += 2
        elif tag in (9, 10, 11, 12, 17, 18): pos += 4
        elif tag == 15: pos += 3
        else: raise ValueError(f'Unexpected constant tag {tag}')
        i += 1
    pos += 6
    interfaces = u2()
    pos += 2 * interfaces
    def table():
        nonlocal pos
        result = set()
        for _ in range(u2()):
            u2(); name = cp[u2()]; desc = cp[u2()]
            result.add((name, desc))
            for _ in range(u2()):
                u2(); size = struct.unpack_from('>I', data, pos)[0]; pos += 4 + size
        return result
    return table(), table()

def verify(original, recovered, mapped_input=False):
    checked = 0
    mapping = {}
    if mapped_input:
        from pathlib import Path
        owner = ''
        for line in Path(__file__).with_name('mixin-reobf.tsrg').read_text().splitlines():
            parts = line.split()
            if not line.startswith('\t'): owner = parts[0]
            else: mapping[(owner, parts[0])] = parts[-1]
    with zipfile.ZipFile(original) as old, zipfile.ZipFile(recovered) as new:
        config = json.loads(new.read('EixClient.mixins.json'))
        for name in config['client'] + config['mixins']:
            entry = (config['package'] + '.' + name).replace('.', '/') + '.class'
            assert entry in new.namelist(), f'Missing mixin: {entry}'
        for entry in old.namelist():
            if entry.startswith('com/heypixel/heypixelmod/mixin/') and entry.endswith('.class'):
                a, b = members(old.read(entry)), members(new.read(entry))
                for before, after in zip(a, b):
                    if mapped_input:
                        expected = {(mapping[(entry[:-6], name)], desc) for name, desc in before if (entry[:-6], name) in mapping}
                    else:
                        expected = {v for v in before if v[0].startswith(('m_', 'f_'))}
                    assert expected <= after, f'Lost SRG mixin members: {entry}: {expected-after}'
                    checked += len(expected)
            elif not entry.endswith('/') and not entry.endswith('.class') and entry != 'META-INF/MANIFEST.MF':
                renamed = entry.replace('VcX6svVqmeT8', 'vcx6svvqmet8')
                assert old.read(entry) == new.read(renamed), f'Changed resource: {entry}'
        result = {
            'comparison_snapshot_sha256': hashlib.sha256(open(original, 'rb').read()).hexdigest(),
            'comparison_snapshot_is_remapped': mapped_input,
            'recovered_sha256': hashlib.sha256(open(recovered, 'rb').read()).hexdigest(),
            'original_classes': sum(p.endswith('.class') for p in old.namelist()),
            'recovered_classes': sum(p.endswith('.class') for p in new.namelist()),
            'mixin_classes': len(config['client']) + len(config['mixins']),
            'srg_members_verified': checked,
            'resources': 'All original non-manifest resources preserved byte-for-byte; resource directory normalized to lowercase',
        }
    print(json.dumps(result, indent=2))

if __name__ == '__main__':
    arguments = [a for a in sys.argv[1:] if a != '--mapped-input']
    verify(*arguments, mapped_input='--mapped-input' in sys.argv)
