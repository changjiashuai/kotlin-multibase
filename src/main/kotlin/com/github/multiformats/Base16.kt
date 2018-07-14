package com.github.multiformats

import kotlin.experimental.and

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/14.
 */
class Base16 {

    companion object {

        fun encode(data: ByteArray): String {
            val sb = StringBuilder()
            data.forEach {
                sb.append(String.format("%02x", it and 0xFF.toByte()))
            }
            return sb.toString()
        }

        fun decode(hex: String): ByteArray {
            val res = ByteArray(hex.length / 2)
            for (i in res.indices) {
                res[i] = Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16).toByte()
            }
            return res
        }
    }
}