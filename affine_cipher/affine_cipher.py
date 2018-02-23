from io import open

def cipher():
    #input variable
    B = int(input("Please enter the value of Beta: "))
    A = int(input("Please enter the value of Alpha: "))
    while(A%2 == 0 or  A%13 == 0):
        A = int(input("Please enter the value of Alpha: "))
    
    print("(" + str(A) + "m + " + str(B) + ") mod26")
    #opens and reads archive, getting rid of the spaces
    arch_i = open('m.txt','r')
    old_array = list(arch_i.read())
    while ' ' in old_array:
        old_array.remove(' ')

    #convertion to ciphered values
    new_array = []

    for element in old_array:
        asc = ord(element)%97
        #new_array.append(chr((asc%26+(k%26))%26+65))   
        new_array.append(chr( ((A * asc + B )%26)+65))       

    #writes the results
    arch_o = open('c.txt','w')
    arch_o.write(u''.join(new_array))

def decipher():
    #input variable
    B = int(input("Please enter the value of Beta: "))
    A = int(input("Please enter the value of Alpha: "))
    while(A%2 == 0 or  A%13 == 0):
        A = int(input("Please enter the value of Alpha: "))

    alphaValues = {1:1,3:9,5:21,7:15,9:3,11:19,15:7,17:23,19:11,21:5,23:17,25:25}

    print(str(alphaValues[A]) + "* ( c + (-" + str(B) + ")) mod26")
    #opens and reads archive
    arch_i = open('c.txt','r')
    old_array = list(arch_i.read())

    #convertion to original values
    new_array = []
    for element in old_array:
        asc = ord(element)%65

        new_array.append(chr((alphaValues[A]*(asc+(-B%26)))%26+97)) 
        
    #writes the results
    arch_o = open('rm.txt','w')
    arch_o.write(u''.join(new_array))

def main():
    op = input("""Please selecte an option: 
    1.-Cipher
    2.-Decipher
    """)
    if(op == "1"):
        cipher()
    else:
        decipher()
    
    
main()