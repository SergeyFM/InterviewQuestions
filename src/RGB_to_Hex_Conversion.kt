/*
Make so that passing in RGB decimal values will result in a hexadecimal representation being returned.
Valid decimal values for RGB are 0 - 255. Any values that fall out of that range must be rounded to the closest valid value.

RGB_to_Hex_Conversion(255, 255, 255) // returns FFFFFF
RGB_to_Hex_Conversion(255, 255, 300) // returns FFFFFF
RGB_to_Hex_Conversion(0, 0, 0) // returns 000000
RGB_to_Hex_Conversion(148, 0, 211) // returns 9400D3
 */

fun main() {
    assertEquals("000000", RGB_to_Hex_Conversion(0, 0, 0))
    assertEquals("000000", RGB_to_Hex_Conversion(0, 0, -20))
    assertEquals("FFFFFF", RGB_to_Hex_Conversion(300,255,255))
    assertEquals("ADFF2F", RGB_to_Hex_Conversion(173,255,47))
    assertEquals("9400D3", RGB_to_Hex_Conversion(148, 0, 211))
    assertEquals("0B0B0B", RGB_to_Hex_Conversion(11, 11, 11))
}

fun RGB_to_Hex_Conversion(r:Int, g:Int, b:Int): String {
    val hex = String.format("%02X%02X%02X",r.coerceIn(0..255),g.coerceIn(0..255),b.coerceIn(0..255))
    return hex
}