
co sum x first numbers co

begin
  read(x) ;
  sum := 0 ;
  if not (x = 0) then
    for i from 0 by 1 to x do 
      sum := sum + i ;
    od ;
  fi
  print(sum)
end