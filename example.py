import pwd
import crypt
import getpass
from hmac import compare_digest as compare_hash

def login():

# algoritmos de cifrado: sha3_224(), sha3_256(), sha3_384(), sha3_512(),
# shake_128(), sha224(), sha256(), sha384(), sha512(), md5()

    import hashlib
    m = hashlib.sha256()
    #updates the hash object with the respective argument
    m.update(b"Hi Bob i've been missing u")
    print(m.digest())
    m.update(b"so much")
    print(m.digest())
    
    print("Digest size: " + str(m.digest_size) + " bytes")
    print("Block size: " + str(m.block_size) + " bytes")

    print("Hex: " + hashlib.sha256(b"Nobody inspects the spammish repetition").hexdigest())


login()
