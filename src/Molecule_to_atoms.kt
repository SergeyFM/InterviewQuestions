/*
For a given chemical formula represented by a string, count the number of atoms of each element contained in the molecule and return an object (associative array in PHP, Dictionary<string, int> in C#, Map<String,Integer> in Java).

For example:

var water = 'H2O';
parseMolecule(water); // return {H: 2, O: 1}

var magnesiumHydroxide = 'Mg(OH)2';
parseMolecule(magnesiumHydroxide); // return {Mg: 1, O: 2, H: 2}

var fremySalt = 'K4[ON(SO3)2]2';
parseMolecule(fremySalt); // return {K: 4, O: 14, N: 2, S: 4}
As you can see, some formulas have brackets in them. The index outside the brackets tells you that you have to multiply count of each atom inside the bracket on this index. For example, in Fe(NO3)2 you have one iron atom, two nitrogen atoms and six oxygen atoms.

Note that brackets may be round, square or curly and can also be nested. Index after the braces is optional.
 */

fun main() {
    val a = listOf("As", "Be", "C", "B", "Co", "O", "Cu").zip(listOf(2, 16, 44, 8, 24, 48, 5)).sortedBy{it.first}.toMap()
    val b = Molecule_to_atoms("As2{Be4C5[BCo3(CO2)3]2}4Cu5")
    assertEquals(a,b)
    
}

fun Molecule_to_atoms(formula: String): Map<String, Int> {
    
    //check brackets
    val bra = mutableListOf<Char>()
    formula.forEach {c ->
        if (c in "[{(") bra.add(c)
        if (c in "]})") {
            val anti_c = when(c) {
                ']' -> '['
                '}' -> '{'
                ')' -> '('
                else -> 'X'
            }
            val last = bra.lastOrNull() ?: 'Y'
            if (bra.size>0 && last==anti_c) bra.removeLast()
            else throw IllegalArgumentException()
        }
    }
    
    //split and edit
    val reagent = getReplaced(formula,"[(]){(})")
        .map { if (it.isUpperCase() || it=='(' || it==')') "█"+it else ""+it }
        .joinToString("").split("█")
        .filter {it.trim().length>0}
        .map {if (it.last().isDigit()) it else it+'1'}
    
    //remove brackets
    val multiplicator = mutableListOf(1)
    val atoms = reagent.reversed().map { part->
        val atom = getAtom(part)
        val num = getNum(part)
        val multi = multiplicator.reduce {acc,n -> acc*n}
        var ret = atom + (num*multi).toString()
        if (part.first() == ')') {multiplicator.add(num); ret = ""}
        if (part.first() == '(' && multiplicator.size > 1) {multiplicator.removeLast(); ret=""}
        ret
    }.filter {it.trim().length>0}.sorted()
    
    //check with the Periodic table
    atoms.forEach {
        if (getAtom(it) !in mendeleev) {
            println("Exception №2 - no such element $it")
            throw IllegalArgumentException() }
    }
    
    //sum all the atoms
    val ret = mutableMapOf<String,Int>().withDefault({0})
    atoms.forEach { part ->
        val atom = getAtom(part)
        val num = getNum(part)
        val existing_num = ret.getValue(atom)
        ret.put(atom,num+existing_num)
    }
    
    return ret
}

fun getAtom (a : String) : String = a.takeWhile {!it.isDigit()}
fun getNum (a : String) : Int = a.takeLastWhile {it.isDigit()}.let {it.toIntOrNull() ?: 0}
fun getReplaced(text : String, to_replace : String) : String =  text.let {
    var r = it
    to_replace.chunked(2) {w ->
        r = r.replace(w.first(),w.last())
    } ; r }
val mendeleev = arrayOf("H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S",
    "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga",
    "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd",
    "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm",
    "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os",
    "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa",
    "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg",
    "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Nh", "Fl", "Mc", "Lv", "Ts", "Og")