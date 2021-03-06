object outcomes {

  def partitions(cards: Array[Int], subtotal: Int): Int = {
    var m = 0
    // Hit
    for (i <- 0 to 9) {
      if (cards(i)>0) {
        cards(i) = cards(i)-1
        var total = subtotal+i+1
        if (total < 21) {
	  // Stand
	  m += 1
	  // Hit again
	  m += partitions(cards, total)
        } else if (subtotal+i+1==21) {
	  // Stand; hit again is an automatic bust
	  m += 1
        }
        cards(i) = cards(i)+1
      }
    }
    return m
  }

  def main(args: Array[String]): Unit = {

    var deck = Array(4,4,4,4,4,4,4,4,4,16)
    var d = 0

    for (i <- 0 to 9) {
      // Dealer showing
      deck(i) = deck(i)-1
      var p = 0
      for (j <- 0 to 9) {
        deck(j) = deck(j)-1
        var n = partitions(deck, j+1)
        deck(j) = deck(j)+1
        p += n
      }
      println(s"Dealer showing $i partitions = $p")
      d += p
      deck(i) = deck(i)+1
    }
    println(s"Total partitions = $d")
  }

}
