// !DIAGNOSTICS: -UNUSED_PARAMETER
open class B<R1, R2>(x: R1, y: R2)

class A0<T1, T2> {
    constructor(x: T1, y: T2): <!CYCLIC_CONSTRUCTOR_DELEGATION_CALL!>this<!>(x, y) {}

    constructor(x: T1, y: T2, z: T2): this(x, 1) {} // ok, delegates to constructor(x: T1, y: Int)

    constructor(x: T1, y: Int): <!NONE_APPLICABLE!>this<!>(x, "") {}
    constructor(x: T1): this(x, 1) {}
}

class A1<T1, T2> : B<T1, T2> {
    constructor(x: T1, y: T2): super(x, y) {}
    constructor(x: T1, y: Int): super(x, <!TYPE_MISMATCH!>y<!>) {}
    constructor(x: T1, y: T1, z: T1): super(x, <!TYPE_MISMATCH!>y<!>) {}
}

class A2<T1, T2> : B<T1, Int> {
    constructor(x: T1, y: T2): super(x, <!TYPE_MISMATCH!>y<!>) {}
    constructor(x: T1, y: Int): super(x, y) {}
    constructor(x: T1, y: T1, z: T1): super(x, <!TYPE_MISMATCH!>y<!>) {}
}

class A3<T1, T2> : B<String, Int> {
    constructor(x: T1, y: T2): super(<!TYPE_MISMATCH!>x<!>, <!TYPE_MISMATCH!>y<!>) {}
    constructor(x: T1, y: Int): super("", y) {}
    constructor(x: String, y: Int): super(x, y) {}
    constructor(x: T1, y: T1, z: T1): <!NONE_APPLICABLE!>this<!>(x, y) {}
    constructor(w: T1, x: T1, y: T1, z: T2): this(w, z) {}
}
