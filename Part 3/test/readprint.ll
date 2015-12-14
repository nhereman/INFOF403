LLVM code :
; External function declaration
declare i32 @getchar()
declare i32 @putchar(i32)

; Defining a function wich read integer
define i32 @readInt() {
entry:
	%res = alloca i32
	%digit = alloca i32
	%mult = alloca i32
	store i32 0, i32* %res
	store i32 1, i32* %mult
	br label %read
read:
	%0 = call i32 @getchar()
	%1 = sub i32 %0, 48
	store i32 %1, i32* %digit
	%2 = icmp ne i32 %0, 10
	br i1 %2, label %save, label %exit
save:
	%3 = load i32* %res
	%4 = load i32* %digit
	%5 = mul i32 %3, 10
	%6 = add i32 %5, %4
	store i32 %6, i32* %res
	br label %read
getminus:
	store i32 -1, i32* %mult
	br label %read
exit:
	%7 = load i32* %res
	%8 = load i32* %mult
	%9 = mul i32 %7, %8
	ret i32 %9
}

; Defining a function wich print integer
define void @putInt(i32 %a) {
entry:
	%size = alloca i32
	%int = alloca i32
	store i32 1, i32* %size
	store i32 %a, i32* %int
	br label %negativetest
negativetest:
	%0 = icmp slt i32 %a, 0
	br i1 %0, label %minus, label %sizecmp
minus:
	call i32 @putchar(i32 45)
	%2 = load i32* %int
	%3 = mul i32 %2, -1
	store i32 %3, i32* %int
	br label %sizecmp
computesize:
	%4 = load i32* %size
	%5 = mul i32 %4, 10
	store i32 %5, i32* %size
	br label %sizecmp
sizecmp:
	%6 = load i32* %size
	%i = load i32* %int
	%7 = icmp ugt i32 %6, %i
	br i1 %7, label %printloop, label %computesize
printloop:
	%j = load i32* %int
	%8 = load i32* %size
	%9 = udiv i32 %8, 10
	store i32 %9, i32* %size
	%10 = udiv i32 %j, %9
	%11 = urem i32 %10, 10
	%12 = add i32 %11, 48
	call i32 @putchar(i32 %12)
	%14 = icmp ugt i32 %9, 1
	br i1 %14, label %printloop, label %exit
exit:
	ret void
}

define i32 @main() {
	%a = alloca i32
	; read var
	%1 = call i32 @readInt()
	store i32 %1, i32* %a
	%b = alloca i32
	; read var
	%2 = call i32 @readInt()
	store i32 %2, i32* %b
	; print var
	%3 = load i32* %a
	call void @putInt(i32 %3)
	; print var
	%4 = load i32* %b
	call void @putInt(i32 %4)
	ret i32 0
}