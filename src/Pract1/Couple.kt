package Pract1

class Couple(stringValue: String, intValue: Int) {
    public var _stringValue = stringValue
    public var _intValue = intValue

    public fun setString(stringValue: String){ this._stringValue = stringValue }
    public fun setInt(intValue: Int){ this._intValue = intValue }

    public fun getString(): String { return this._stringValue }
    public fun getInt(): Int { return this._intValue}
}