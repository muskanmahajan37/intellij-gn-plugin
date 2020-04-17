//  Copyright (c) 2020 Google LLC All rights reserved.
//  Use of this source code is governed by a BSD-style
//  license that can be found in the LICENSE file.
package com.google.idea.gn.psi

import com.google.idea.gn.completion.CompletionIdentifier
import com.google.idea.gn.psi.scope.Scope

abstract class Function : CompletionIdentifier {
  abstract fun execute(call: GnCall,
                       targetScope: Scope)

  abstract val isBuiltin: Boolean
  open val variables: Map<String, FunctionVariable> = emptyMap()

  override val insertionText: String
    get() = "$name(\"\")"
  override val caretShift: Int
    get() = -2

  override fun gatherChildren(operator: (CompletionIdentifier) -> Unit) =
      variables.forEach { operator(it.value) }
}