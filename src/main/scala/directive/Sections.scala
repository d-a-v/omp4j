package org.omp4j.directive

import org.antlr.v4.runtime.{TokenStreamRewriter, Token}
import org.omp4j.directive.DirectiveSchedule._
import org.omp4j.grammar.Java8Parser
import org.omp4j.tree.{OMPClass, OMPVariable}

import scala.collection.mutable.ListBuffer
import org.omp4j.Config

case class Sections(override val parent: Directive)(implicit schedule: DirectiveSchedule, ctx: Java8Parser.StatementContext, cmt: Token, line: Int, conf: Config) extends Directive(parent, List(), List()) {
	override lazy val secondIter = true

	/** Translate directives of type Section */
	override protected def translateChildren(captured: Set[OMPVariable], capturedThis: Boolean, directiveClass: OMPClass)(implicit rewriter: TokenStreamRewriter) = {
		super.translateChildren(captured, capturedThis, directiveClass)
		childrenOfType[Section].zipWithIndex.foreach{ case (s, i) =>
			s.postTranslate(i)
		}
	}

	override protected def postTranslate(captured: Set[OMPVariable], capturedThis: Boolean, directiveClass: OMPClass)(implicit rewriter: TokenStreamRewriter) = {
		wrap(rewriter)(captured, capturedThis, directiveClass)
	}

}
