package com.github.multiformats

import com.github.multiformats.MultiBase.Base.*
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
            BASE1 -> BaseN.encode(base.alphabet, BigInteger("1"), data)
            BASE2 -> BaseN.encode(base.alphabet, BigInteger("2"), data)
            BASE8 -> BaseN.encode(base.alphabet, BigInteger("8"), data)
            BASE10 -> BaseN.encode(base.alphabet, BigInteger("10"), data)
            BASE16 -> BaseN.encode(base.alphabet, BigInteger("16"), data)
            BASE16_UPPER -> BaseN.encode(base.alphabet, BigInteger("16"), data)
            BASE32 -> BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_UPPER -> BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_PAD -> Base32.encodeWithPad(data)
            BASE32_PAD_UPPER -> BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX -> BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX_UPPER -> BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX_PAD -> org.apache.commons.codec.binary.Base32(true).encodeToString(data).toLowerCase()
            BASE32_HEX_PAD_UPPER -> BaseN.encode(base.alphabet, BigInteger("32"), data)
            BASE58_FLICKR -> BaseN.encode(base.alphabet, BigInteger("58"), data)
            BASE58_BTC -> BaseN.encode(base.alphabet, BigInteger("58"), data)
            BASE64 -> BaseN.encode(base.alphabet, BigInteger("64"), data)
            BASE64_URL -> BaseN.encode(base.alphabet, BigInteger("64"), data)
            BASE64_PAD -> BaseN.encode(base.alphabet, BigInteger("64"), data)
            BASE64_URL_PAD -> BaseN.encode(base.alphabet, BigInteger("64"), data)
        }
    }

    fun decode(data: String): ByteArray {
        val prefix = data[0]
        val base = Base.lookup(prefix)
        return when (base) {
            BASE1 -> BaseN.decode(base.alphabet, BigInteger("1"), data)
            BASE2 -> BaseN.decode(base.alphabet, BigInteger("2"), data)
            BASE8 -> BaseN.decode(base.alphabet, BigInteger("8"), data)
            BASE10 -> BaseN.decode(base.alphabet, BigInteger("10"), data)
            BASE16 -> BaseN.decode(base.alphabet, BigInteger("16"), data)
            BASE16_UPPER -> BaseN.decode(base.alphabet, BigInteger("16"), data)
            BASE32 -> BaseN.decode(base.alphabet, BigInteger("32"), data)
            BASE32_UPPER -> BaseN.decode(base.alphabet, BigInteger("32"), data)
            BASE32_PAD -> Base32.decodeWithPad(data)
            BASE32_PAD_UPPER -> BaseN.decode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX -> BaseN.decode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX_UPPER -> BaseN.decode(base.alphabet, BigInteger("32"), data)
            BASE32_HEX_PAD -> org.apache.commons.codec.binary.Base32(true).decode(data)
            BASE32_HEX_PAD_UPPER -> BaseN.decode(base.alphabet, BigInteger("32"), data)
            BASE58_FLICKR -> BaseN.decode(base.alphabet, BigInteger("58"), data)
            BASE58_BTC -> BaseN.decode(base.alphabet, BigInteger("58"), data)
            BASE64 -> BaseN.decode(base.alphabet, BigInteger("64"), data)
            BASE64_URL -> BaseN.decode(base.alphabet, BigInteger("64"), data)
            BASE64_PAD -> BaseN.decode(base.alphabet, BigInteger("64"), data)
            BASE64_URL_PAD -> BaseN.decode(base.alphabet, BigInteger("64"), data)
        }
    }
}