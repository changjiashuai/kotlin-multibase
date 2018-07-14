package com.github.multiformats

import org.apache.commons.codec.binary.Base32

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/14.
 */
object Base32 {

    private val base32 = Base32()

    fun encodeWithPad(data: ByteArray): String {
        return base32.encodeToString(data).toLowerCase()
    }

    fun decodeWithPad(data: String): ByteArray {
        return base32.decode(data)
    }
}