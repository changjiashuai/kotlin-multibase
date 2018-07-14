package io.ipfs.multiformats.multibase

import io.ipfs.multiformats.multibase.MultiBase.Base.*
import org.apache.commons.codec.binary.Base32
import java.math.BigInteger
import java.util.*


/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/12.
 *
 * https://www.ietf.org/rfc/rfc4648.txt
 */
object MultiBase {

    enum class Base(val prefix: Char, val alphabet: String) {
        BASE1('1', "1"),
        BASE2('0', "01"),
        BASE8('7', "01234567"),
        BASE10('9', "0123456789"),
        BASE16('f', "0123456789abcdef"),
        BASE16_UPPER('F', "0123456789ABCDEF"),
        BASE32('b', "abcdefghijklmnopqrstuvwxyz234567"),
        BASE32_UPPER('B', "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567"),
        BASE32_PAD('c', "abcdefghijklmnopqrstuvwxyz234567="),
        BASE32_PAD_UPPER('C', "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567="),
        BASE32_HEX('v', "0123456789abcdefghijklmnopqrstuvw"),
        BASE32_HEX_UPPER('V', "0123456789ABCDEFGHIJKLMNOPQRSTUVW"),
        BASE32_HEX_PAD('t', "0123456789abcdefghijklmnopqrstuvw="),
        BASE32_HEX_PAD_UPPER('T', "0123456789ABCDEFGHIJKLMNOPQRSTUVW="),
        BASE58_FLICKR('Z', "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"),
        BASE58_BTC('z', "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ"),
        BASE64('m', "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"),
        BASE64_URL('u', "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_"),
        BASE64_PAD('M', "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="),
        BASE64_URL_PAD('U', "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=");

        companion object {

            private val baseMap = TreeMap<Char, Base>()

            init {
                for (base in values()) {
                    baseMap[base.prefix] = base
                }
            }

            fun lookup(prefix: Char): Base {
                return baseMap[prefix]
                        ?: throw IllegalStateException("Unknown Multibase type: $prefix")
            }
        }
    }


    fun encode(base: Base, data: ByteArray): String {
        return when (base) {
//            BASE1 -> base.prefix + BaseN.encode(base.alphabet, BigInteger("1"), data)
//            BASE2 -> base.prefix + String(BinaryCodec().encode(data))
//            BASE8 -> base.prefix + BaseN.encode(base.alphabet, BigInteger("8"), data)
//            BASE10 -> base.prefix + BaseN.encode(base.alphabet, BigInteger("10"), data)
            BASE16 -> base.prefix + BaseN.encode(base.alphabet, BigInteger("16"), data)
            BASE16_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInteger("16"), data)
            BASE32 -> base.prefix + BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_PAD -> base.prefix + Base32().encodeToString(data).toLowerCase()
            BASE32_PAD_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX -> base.prefix + BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX_PAD -> base.prefix + Base32(true).encodeToString(data).toLowerCase()
            BASE32_HEX_PAD_UPPER -> base.prefix + Base32(true).encodeToString(data)
            BASE58_FLICKR -> base.prefix + BaseN.encode(base.alphabet, BigInteger("58"), data)
            BASE58_BTC -> base.prefix + BaseN.encode(base.alphabet, BigInteger("58"), data)
            BASE64 -> base.prefix + BaseN.encode(base.alphabet, BigInteger("64"), data)
            BASE64_URL -> base.prefix + BaseN.encode(base.alphabet, BigInteger("64"), data)
            BASE64_PAD -> base.prefix + Base64.getEncoder().encodeToString(data)
            BASE64_URL_PAD -> base.prefix + Base64.getUrlEncoder().encodeToString(data)
            else -> throw IllegalStateException("UnImplement multi type")
        }
    }

    fun decode(data: String): ByteArray {
        val prefix = data[0]
        val rest = data.substring(1)
        val base = Base.lookup(prefix)
        return when (base) {
//            BASE1 -> BaseN.decode(base.alphabet, BigInteger("1"), rest)
//            BASE2 -> BinaryCodec().decode(rest.toByteArray())
//            BASE8 -> BaseN.decode(base.alphabet, BigInteger("8"), rest)
//            BASE10 -> BaseN.decode(base.alphabet, BigInteger("10"), rest)
            BASE16 -> BaseN.decode(base.alphabet, BigInteger("16"), rest)
            BASE16_UPPER -> BaseN.decode(base.alphabet, BigInteger("16"), rest)
            BASE32 -> BaseN.decode(base.alphabet, BigInteger("32"), rest)
            BASE32_UPPER -> BaseN.decode(base.alphabet, BigInteger("32"), rest)
            BASE32_PAD -> Base32().decode(rest)
            BASE32_PAD_UPPER -> BaseN.decode(base.alphabet, BigInteger("32"), rest)
            BASE32_HEX -> BaseN.decode(base.alphabet, BigInteger("32"), rest)
            BASE32_HEX_UPPER -> BaseN.decode(base.alphabet, BigInteger("32"), rest)
            BASE32_HEX_PAD -> Base32(true).decode(rest)
            BASE32_HEX_PAD_UPPER -> Base32(true).decode(rest)
            BASE58_FLICKR -> BaseN.decode(base.alphabet, BigInteger("58"), rest)
            BASE58_BTC -> BaseN.decode(base.alphabet, BigInteger("58"), rest)
            BASE64 -> BaseN.decode(base.alphabet, BigInteger("64"), rest)
            BASE64_URL -> BaseN.decode(base.alphabet, BigInteger("64"), rest)
            BASE64_PAD -> Base64.getDecoder().decode(rest)
            BASE64_URL_PAD -> Base64.getDecoder().decode(rest)
            else -> throw IllegalStateException("UnImplement multi type")
        }
    }
}