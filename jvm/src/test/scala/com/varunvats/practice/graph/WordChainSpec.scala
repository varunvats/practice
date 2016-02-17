package com.varunvats.practice.graph

import com.varunvats.practice.sorting.UnitSpec

class WordChainSpec extends UnitSpec {

  val dictionary = io.Source.fromInputStream(getClass.getResourceAsStream("/wordlist.txt")).getLines().toSet

  "The longest word finder" must {
    "return 'ministers' when the seed is 'i'" in {
      WordChain.findLongestValidWord('i', dictionary) shouldBe "ministers"
    }

    "return 'ministers' when the seed is 's'" in {
      WordChain.findLongestValidWord('i', dictionary) shouldBe "ministers"
    }

    "return 'completed' when the seed is 't'" in {
      WordChain.findLongestValidWord('t', dictionary) shouldBe "completed"
    }

    "return 'soldierly' when the seed is 'd'" in {
      WordChain.findLongestValidWord('d', dictionary) shouldBe "soldierly"
    }

    "return 'zeno' when the seed is 'z'" in {
      WordChain.findLongestValidWord('z', dictionary) shouldBe "zeno"
    }

    "return 'maximis' when the seed is 'x'" in {
      WordChain.findLongestValidWord('x', dictionary) shouldBe "maximis"
    }

    "return 'prayers' when the seed is 'y'" in {
      WordChain.findLongestValidWord('y', dictionary) shouldBe "prayers"
    }

    "return 'jewfish' when the seed is 'j'" in {
      WordChain.findLongestValidWord('j', dictionary) shouldBe "jewfish"
    }
  }
}
