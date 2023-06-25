import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Set1Test {

    @Test
    fun step1() {
        val converted = step1("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")
        assertEquals("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t", converted)
    }

    @Test
    fun step2() {
        val xord = step2("1c0111001f010100061a024b53535009181c", "686974207468652062756c6c277320657965")
        assertEquals("746865206b696420646f6e277420706c6179", xord)
    }

    @Test
    fun xor() {
        val xord = xor("1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736".fromHex(), "X"[0]);
        assertEquals("Cooking MC's like a pound of bacon", xord)
    }

    @Test
    fun step3() {
        val ciphertext = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
        val plain = step3(ciphertext)
        assertEquals("Cooking MC's like a pound of bacon", plain.maxBy { it.second }.first)

    }

    @Test
    fun step4() {
        val plaintext = step4("src/test/resources/step4.txt")
        assertEquals("Now that the party is jumping\n", plaintext.first)
    }

    @Test
    fun step5() {
        val plaintext = step5(
            "Burning 'em, if you ain't quick and nimble\n" +
            "I go crazy when I hear a cymbal", "ICE")
        assertEquals("0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f", plaintext)
    }
}
