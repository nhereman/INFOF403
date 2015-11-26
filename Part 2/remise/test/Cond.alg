co Parser Test File ExprArith co

begin
	read(a);
	read(b);
	read(c);
	if a = b and c + 1 /= 2 or not c/2 = 4 then
		print(a)
	else
		print(c)
	fi;
	print(b) 
end