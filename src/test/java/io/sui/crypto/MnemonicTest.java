package io.sui.crypto;

import com.google.common.io.BaseEncoding;
import io.sui.account.Account;
import io.sui.account.ED25519;
import io.sui.account.Node;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.*;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MnemonicTest {

    private String seedString = "000102030405060708090a0b0c0d0e0f";

    private String m_priv = "2b4be7f19ee27bbf30c667b642d5f4aa69fd169872f8fc3059c08ebae2eb19e7";
    private String m_chain = "90046a93de5380a72b5e45010748567d5ea02bbf6522f979e05c0d8d8ca9fffb";
    private String m_pub = "a4b2856bfec510abab89753fac1ac0e1112364e7d250545963f135f2a33188ed";

    private String m_0_priv = "68e0fe46dfb67e368c75379acec591dad19df3cde26e63b93a8e704f1dade7a3";
    private String m_0_chain = "8b59aa11380b624e81507a27fedda59fea6d0b779a778918a2fd3590e16e9c69";
    private String m_0_pub = "008c8a13df77a28f3445213a0f432fde644acaa215fc72dcdf300d5efaa85d350c";

    private String m_0_1_priv = "b1d0bad404bf35da785a64ca1ac54b2617211d2777696fbffaf208f746ae84f2";
    private String m_0_1_chain = "a320425f77d1b5c2505a6b1b27382b37368ee640e3557c315416801243552f14";

    private String m_0_1_2_priv = "92a5b23c0b8a99e37d07df3fb9966917f5d06e02ddbd909c7e184371463e9fc9";
    private String m_0_1_2_chain = "2e69929e00b5ab250f49c3fb1c12f252de4fed2c1db88387094a0f8c4c9ccd6c";

    private String m_0_1_2_2_priv = "30d1dc7e5fc04c31219ab25a27ae00b50f6fd66622f6e9c913253d6511d1e662";
    private String m_0_1_2_2_chain = "8f6d87f93d750e0efccda017d662a1b31a266e4a6f5993b15f5c1f07f74dd5cc";

    private String m_0_1_2_2_1000000000_priv = "8f94d394a8e8fd6b1bc2f3f49f5c47e385281d5c17e65324b0f62483e37e8793";
    private String m_0_1_2_2_1000000000_chain = "68789923a0cac2cd5a29172a475fe9e0fb14cd6adb5ad98a3fa70333e7afa230";




    @Test
    void hex() throws Exception {

//        HDPath path = HDPath.parsePath("m/44'/784'/0'/0'/0'");
//        System.out.println(path.get(0));
        byte[] seed = BaseEncoding.base16().decode(seedString.toUpperCase());
        ED25519 master = new ED25519(seed);
        Ed25519PrivateKeyParameters privateKeyParameters = new Ed25519PrivateKeyParameters(master.masterKey.getKey());
        Ed25519PublicKeyParameters publicKeyParameters = privateKeyParameters.generatePublicKey();
        ED25519KeyPair keyPair = new ED25519KeyPair(privateKeyParameters, publicKeyParameters);
        System.out.println(keyPair.publicKeyBytes().length);
        System.out.println(m_pub.length());
        assertEquals(m_pub.toUpperCase().substring(2),BaseEncoding.base16().encode(keyPair.publicKeyBytes()));
        assertEquals(m_priv.toUpperCase(), BaseEncoding.base16().encode(master.masterKey.getKey()));
        assertEquals(m_chain.toUpperCase(), BaseEncoding.base16().encode(master.masterKey.getChaincode()));

        Node m0 = master.derive(master.masterKey, 0);
        Ed25519PrivateKeyParameters moKey = new Ed25519PrivateKeyParameters(m0.getKey());
        ED25519KeyPair m0keyPair = new ED25519KeyPair(moKey, moKey.generatePublicKey());
        assertEquals(BaseEncoding.base16().encode(m0.getKey()), m_0_priv.toUpperCase());
        assertEquals(BaseEncoding.base16().encode(m0.getChaincode()), m_0_chain.toUpperCase());
        assertEquals(m_0_pub.toUpperCase().substring(2),BaseEncoding.base16().encode(m0keyPair.publicKeyBytes()));

        Node m01 = master.derive(m0, 1);
        assertEquals(BaseEncoding.base16().encode(m01.getKey()), m_0_1_priv.toUpperCase());
        assertEquals(BaseEncoding.base16().encode(m01.getChaincode()), m_0_1_chain.toUpperCase());

        Node m012 = master.derive(m01, 2);
        assertEquals(BaseEncoding.base16().encode(m012.getKey()), m_0_1_2_priv.toUpperCase());
        assertEquals(BaseEncoding.base16().encode(m012.getChaincode()), m_0_1_2_chain.toUpperCase());

        Node m0122 = master.derive(m012, 2);
        assertEquals(BaseEncoding.base16().encode(m0122.getKey()), m_0_1_2_2_priv.toUpperCase());
        assertEquals(BaseEncoding.base16().encode(m0122.getChaincode()), m_0_1_2_2_chain.toUpperCase());

        Node m01221000000000 = master.derive(m0122, 1000000000);
        assertEquals(BaseEncoding.base16().encode(m01221000000000.getKey()), m_0_1_2_2_1000000000_priv.toUpperCase());
        assertEquals(BaseEncoding.base16().encode(m01221000000000.getChaincode()), m_0_1_2_2_1000000000_chain.toUpperCase());

    }

    // ED25519  0x4e0cc5c559ee61c36d61d0624c924cc43348b764
    // "feel acid liar execute insane midnight oval oyster slot uncle bitter person"
    //  m/44'/784'/0'/0'/0'

    @Test
    void testEd25519() throws Exception {
        String mnemonic = "feel acid liar execute insane midnight oval oyster slot uncle bitter person";
        byte[] seed = Account.toSeed(mnemonic, "");
        ED25519 master = new ED25519(seed);
        Node m44 = master.derive(master.masterKey, 44);
        Node m784 = master.derive(m44, 784);
        Node m7840 = master.derive(m784, 0);
        Node m78400 = master.derive(m7840, 0);
        Node m784000 = master.derive(m78400, 0);
        Ed25519PrivateKeyParameters privateKeyParameters = new Ed25519PrivateKeyParameters(m784000.getKey());
        Ed25519PublicKeyParameters publicKeyParameters = privateKeyParameters.generatePublicKey();
        ED25519KeyPair keyPair = new ED25519KeyPair(privateKeyParameters, publicKeyParameters);
        assertEquals("0x4e0cc5c559ee61c36d61d0624c924cc43348b764", keyPair.address());
    }

    // Secp256k1  0x6604964784bd9792e53dca3750d29ab39fb053e5
    // heart position turkey bus virtual host panther pioneer ready lesson fence what
    // m/54'/784'/0'/0/0
    @Test
    void testSecp256k1() {
        String mnemonic = "heart position turkey bus virtual host panther pioneer ready lesson fence what";
        byte[] seed = MnemonicCode.toSeed(Arrays.asList(mnemonic.split(" ")), "");
        DeterministicKey deterministicKey = HDKeyDerivation.createMasterPrivateKey(seed);

        DeterministicKey key1 = HDKeyDerivation
                .deriveChildKey(deterministicKey, new ChildNumber(54, true));
        DeterministicKey key2 = HDKeyDerivation
                .deriveChildKey(key1, new ChildNumber(784, true));
        DeterministicKey key3 = HDKeyDerivation
                .deriveChildKey(key2, new ChildNumber(0, true));
        DeterministicKey key4 = HDKeyDerivation
                .deriveChildKey(key3, new ChildNumber(0, false));
        DeterministicKey key5 = HDKeyDerivation
                .deriveChildKey(key4, new ChildNumber(0, false));

        SECP256K1KeyPair keyPair = new SECP256K1KeyPair(key5.getPrivKey().toByteArray());
        System.out.println(keyPair.address());
    }
}