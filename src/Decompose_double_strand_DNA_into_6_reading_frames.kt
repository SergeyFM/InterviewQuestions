/*
In a double strand DNA you find 3 more Reading frames than the single strand DNA reading frames base on the reverse complement-strand.

Input
Given a DNA sequence like the following

AGGTGACACCGCAAGCCTTATATTAGC
Processing
In the reverse complement the following transformations are made

A-->T
G-->C
T-->A
C-->G
Due to the splicing of DNA strands and the fixed reading direction of a nucleotide strand, the reverse complement gets read from right to left.

DNA                     AGGTGACACCGCAAGCCTTATATTAGC
Reverse complement:     TCCACTGTGGCGTTCGGAATATAATCG
reversed reverse frame: GCTAATATAAGGCTTGCGGTGTCACCT
Output
You'll have to output:

Frame 1: AGG TGA CAC CGC AAG CCT TAT ATT AGC
Frame 2: A GGT GAC ACC GCA AGC CTT ATA TTA GC
Frame 3: AG GTG ACA CCG CAA GCC TTA TAT TAG C

Reverse Frame 1: GCT AAT ATA AGG CTT GCG GTG TCA CCT
Reverse Frame 2: G CTA ATA TAA GGC TTG CGG TGT CAC CT
Reverse Frame 3: GC TAA TAT AAG GCT TGC GGT GTC ACC T

 */

fun main() {
    assertEquals(
                "Frame 1: AGG TGA CAC CGC AAG CCT TAT ATT AGC\n" +
                "Frame 2: A GGT GAC ACC GCA AGC CTT ATA TTA GC\n" +
                "Frame 3: AG GTG ACA CCG CAA GCC TTA TAT TAG C\n\n" +
                "Reverse Frame 1: GCT AAT ATA AGG CTT GCG GTG TCA CCT\n" +
                "Reverse Frame 2: G CTA ATA TAA GGC TTG CGG TGT CAC CT\n" +
                "Reverse Frame 3: GC TAA TAT AAG GCT TGC GGT GTC ACC T",
        Decompose_double_strand_DNA_into_6_reading_frames("AGGTGACACCGCAAGCCTTATATTAGC"))
   
}

fun Decompose_double_strand_DNA_into_6_reading_frames(d : String) : String {
    val frames = getDecomposed(d, "Frame")
    val rFrames = getDecomposed(getComplemented(d).reversed(), "Reverse Frame")
    return "$frames\n\n$rFrames"
}

fun getDecomposed(d : String, label : String) : String = (0..2).map {
    "$label ${it+1}: " + (d.take(it) + " " + d.drop(it).chunked(3).joinToString(" ")).trim()
}.joinToString("\n")

fun getComplemented(d : String) : String = d.map {
    when (it) {
        'A'->'T'
        'G'->'C'
        'T'->'A'
        'C'->'G'
        else -> it }
}.joinToString("")