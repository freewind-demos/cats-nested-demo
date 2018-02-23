package example

import cats.Functor
import cats.data.Nested
import cats.implicits._

object Hello extends App {

  val listOption: List[Option[Int]] = List(Some(1), None)

  val f: Int => String = i => (i * 2).toString
  val x = Functor[List].map(listOption)(opt => opt.map(f))
  println(x)

  val nested: Nested[List, Option, Int] = Nested(listOption)
  type F[T] = Nested[List, Option, T]
  val result: Nested[List, Option, String] = Functor[F].map(nested)(f)
  println(result.value)

  // This line shows error in IDEA, but compilable with `scalac`
  val y = nested.map(x => "ss").value
  println(y)

  //  for {
  //    x <- Nested[List, Option, Int](List(Option(3)))
  //    y <- Nested[List, Option, Int](List(Option(3)))
  //    z <- Nested[Option, Option, Int](Option(Option(3)))
  //  } yield {
  //    println(x)
  //    println(y)
  //    println(z)
  //  }

//  Nested[List, Option, Int](List(Option(3))).mapK[Option] { k => Option(k) }
  //  Nested[List, Option, Int](List(Option(3))).mapK { x =>
  //    Nested[List, Option, Int](List(Option(4))).map { y =>
  //      Nested[Option, Option, Int](Option(Option(5))).map { z =>
  //        println(x)
  //        println(y)
  //        println(z)
  //      }
  //    }
  //  }
}
