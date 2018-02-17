from io import open

def cipher():
    #input variable
    k= int(input("Please enter your key: "))

    #opens and reads archive, getting rid of the spaces
    arch_i = open('m.txt','r')
    old_array = list(arch_i.read())
    while ' ' in old_array:
        old_array.remove(' ')

    #convertion to ciphered values
    new_array = []
    for element in old_array:
        asc = ord(element)%97
        new_array.append(chr((asc%26+(k%26))%26+65))   

    #writes the results
    print(new_array)
    arch_o = open('c.txt','w')
    arch_o.write(u''.join(new_array))

def decipher():
    #input variable
    k= int(input("Please enter your key: "))

    #opens and reads archive
    arch_i = open('c.txt','r')
    old_array = list(arch_i.read())

    #convertion to original values
    new_array = []
    for element in old_array:
        asc = ord(element)%65
        new_array.append(chr((asc%26+(-k%26))%26+97))   

    #writes the results
    arch_o = open('rm.txt','w')
    arch_o.write(u''.join(new_array))

def main():
    cipher()
    decipher()
main()