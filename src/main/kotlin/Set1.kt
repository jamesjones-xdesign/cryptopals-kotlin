@file:OptIn(ExperimentalEncodingApi::class)

import java.io.File
import kotlin.experimental.xor
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

fun step1(hex: String): String {
    val bytes = hex.fromHex()
    return bytes.base64()
}

fun step2(input: String, cipher: String): String {
    val left = input.fromHex()
    val right = cipher.fromHex()
    return left.zip(right).map { it.first.toInt().xor(it.second.toInt())}.toIntArray().toHex()
}

fun step3(input: String): List<Pair<String, Double>> {
    val ciphertext = input.fromHex();
    val keys = ('A'..'z').toMutableList()
    keys.addAll(('0'..'9'))
    return keys.map { xor(ciphertext, it) }
        .map { englishScore(it) }
}

fun step4(path: String): Pair<String, Double> {
    return File(path).readLines().flatMap { step3(it) }.maxBy { it.second }
}

/**
 * Weights each alphabetical character and a white space as 1 and any other character as zero and sums to
 * calc a score for a piece of text.
 */
fun englishScore(text: String): Pair<String, Double> {
    val score = text.filter { it in ('A'..'z') || it == ' ' }
        .groupingBy { it.lowercaseChar() }
        .eachCount().toList()
        .sumOf { it.second }
    return Pair(text, score.toDouble().div(text.length.toDouble()))
}

fun step5Old(input: String, key: String): String {
    val cipher = key.repeat(input.length / key.length);
    val enc = input.zip(cipher).map { it.first.code.toByte().xor(it.second.code.toByte()) }.toByteArray()
    return String(enc).toHex()
}

fun step5(input: String, key: String): String {
    val enc = input.chunked(3).flatMap { it.zip(key).map { pair -> pair.first.code.toByte().xor(pair.second.code.toByte()) } }.toByteArray()
    return String(enc).toHex()
}

fun xor(input: ByteArray, char: Char): String {
    return String(input.map { it.toInt().xor(char.code).toByte() }.toByteArray())
}

fun IntArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

fun String.toHex(): String {
    return map { it.code }.joinToString("") { "%02x".format(it) }
}

fun String.fromHex(): ByteArray {
    check(length % 2 == 0)
    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}

fun ByteArray.base64(): String {
    return Base64.encode(this)
}
