public class modular {
	
	private static long inv = 0;
	 
	/*
	     * Procedimiento que calcula el módulo inverso: (a^-1)( mod n)
	     * pero devuelve false, si no existe el inverso o true en caso
	     * contrario.
	     * Si el valor del inverso es calculado, entonces es almacenado
	     * en la variable global inv
	     **/
	    public static boolean moduloInverso(long a, long n)    
	    {
	           long[] resp = new long[3];           
	           resp = euclidesExtendido(a,n);
	           
	           if(resp[0]>1)
	                return false;	                
	           else
	           {
	               if(resp[1]<0)
	                   inv = resp[1]+n;
	               else
	                   inv = resp[1];
	           
	               return true;
	           }
	    } 
	    
	    /* procedimiento que calcula el valor del maximo común divisor
	    * de a y b siguiendo el algoritmo de euclides extendido,
	    * devolviendo en un arreglo la siguiente estructura [d,x,y]
	    * donde:
	    *    mcd(a,b) = d = a*x + b*y
	    **/	


	    public static long[] euclidesExtendido(long a, long b)	
	    {
	         long[] resp = new long[3];
	         long x=0,y=0,d=0;
	    		
	        if(b==0)
	    	{
	    		resp[0] = a;	
				resp[1] = 1;	
				resp[2] = 0;
	        }		
	        else
	        {
	    		long x2 = 1, x1 = 0, y2 = 0, y1 = 1;
	    		long q = 0, r = 0;	    			
				while(b>0)
				{
					q = (a/b);
					r = a - q*b;
					x = x2-q*x1;
					y = y2 - q*y1;
					a = b;
					b = r;
					x2 = x1;
					x1 = x;
					y2 = y1;
					y1 = y;
					System.out.println("" + a/b);
				}
						
				resp[0] = a;
				resp[1] = x2;
				resp[2] = y2;
				System.out.println("" + resp[0]);
				System.out.println("" + resp[1]);
				System.out.println("" + resp[2]);
	        }
	     
	        return resp;		
	    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numero = 97;
		int modulo = 239;
		if(moduloInverso(modulo, numero)){
			System.out.println("La solucion es: "+inv);
		}
		else
			System.out.println("NO EXISTE SOLUCION");
	}

}