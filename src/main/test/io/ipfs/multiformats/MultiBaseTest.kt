package io.ipfs.multiformats

import io.ipfs.multiformats.multibase.BaseN
import io.ipfs.multiformats.multibase.MultiBase
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/14.
 */
class MultiBaseTest {

    var encodedSamples = hashMapOf(
//            MultiBase.Base.BASE1 to "1",
//            MultiBase.Base.BASE2 to "000000001",
//            MultiBase.Base.BASE8 to "7074432",
//            MultiBase.Base.BASE10 to "909888",
            MultiBase.Base.BASE16 to "f446563656e7472616c697a652065766572797468696e67212121",
            MultiBase.Base.BASE16_UPPER to "F446563656E7472616C697A652065766572797468696E67212121",
            MultiBase.Base.BASE32 to "birswgzloorzgc3djpjssazlwmvzhs5dinfxgoijbee",
            MultiBase.Base.BASE32_UPPER to "BIRSWGZLOORZGC3DJPJSSAZLWMVZHS5DINFXGOIJBEE",
            MultiBase.Base.BASE32_PAD to "cirswgzloorzgc3djpjssazlwmvzhs5dinfxgoijbee======",
            MultiBase.Base.BASE32_HEX to "v8him6pbeehp62r39f9ii0pbmclp7it38d5n6e89144",
            MultiBase.Base.BASE32_HEX_UPPER to "V8HIM6PBEEHP62R39F9II0PBMCLP7IT38D5N6E89144",
            MultiBase.Base.BASE32_HEX_PAD to "t8him6pbeehp62r39f9ii0pbmclp7it38d5n6e89144======",
            MultiBase.Base.BASE32_HEX_PAD_UPPER to "T8HIM6PBEEHP62R39F9II0PBMCLP7IT38D5N6E89144======",
            MultiBase.Base.BASE58_FLICKR to "Z36UQrhJq9fNDS7DiAHM9YXqDHMPfr4EMArvt",
            MultiBase.Base.BASE58_BTC to "z36UQrhJq9fNDS7DiAHM9YXqDHMPfr4EMArvt",
            MultiBase.Base.BASE64 to "mRGVjZW50cmFsaXplIGV2ZXJ5dGhpbmchISE",
            MultiBase.Base.BASE64_URL to "uRGVjZW50cmFsaXplIGV2ZXJ5dGhpbmchISE",
            MultiBase.Base.BASE64_PAD to "MRGVjZW50cmFsaXplIGV2ZXJ5dGhpbmchISE=",
            MultiBase.Base.BASE64_URL_PAD to "URGVjZW50cmFsaXplIGV2ZXJ5dGhpbmchISE="
    )

    @Test
    fun testBase() {
        encodedSamples.forEach { base, input ->
            val origin = MultiBase.decode(input)
            val encode = MultiBase.encode(base, origin)
            Assert.assertEquals(encode, input)
        }
    }

    @Test
    fun testDecodeToBigInteger() {
        val bi = BaseN.decodeToBigInteger("abcdefghijklmnopqrstuvwxyz234567=", BigInteger("32"), "===")
        println("bi=$bi")
    }
}