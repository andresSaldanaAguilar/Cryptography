from io import open

#input variable
k= int(input("Please enter your key: "))

#opens and reads archive, getting rid of the spaces
arch_i = open('m.txt','r')
old_array = list(arch_i.read())
while ' ' in old_array:
    old_array.remove(' ')

#convertion to new values
new_array = []
for element in old_array:
    asc = ord(element)-32+k
    #right limit
    if asc > 90:
        new_array.append(chr(asc-26))
    #left limit
    elif asc < 65:
        new_array.append(chr(asc+26))
    else:
        new_array.append(chr(asc))       

#writes the results
arch_o = open('c.txt','w')
arch_o.write(u''.join(new_array))