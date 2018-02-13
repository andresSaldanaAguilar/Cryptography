from io import open

#input variable
k=int(input(""" 
Positive k: left shift
Negative k: right shift
Please enter the value for k: """))

#opens and reads archive
arch_i = open('m.txt','r')
old_array = list(arch_i.read())

#convertion to new values
new_array = []
for element in old_array:
    asc = ord(element)-32+k
    #righ limit
    if asc > 90:
        new_array.append(chr(asc-26))
    #left limit
    elif asc < 65:
        new_array.append(chr(asc+26))
    else:
        new_array.append(chr(asc))
    

print(new_array)
#writes the results
arch_o = open('c.txt','w')
arch_o.write(''.join(new_array))