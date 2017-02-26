public class ByteRepresentation {

    /**
     * return boolean array of passed byte
     * 
     * @param nbyte
     *            byte number
     * @return boolean array representation
     */
    public static boolean[] returnRepresentation(int nbyte) {
        if (nbyte < 0 || nbyte > 254) {
            throw new IllegalArgumentException("argument < 0 or > 254");
        }

        boolean[] representation = new boolean[8];

        int divisor = 128;

        for (int i = 7; i >= 0; i--) {
            if (nbyte >= divisor) {
                representation[i] = true;
                nbyte -= divisor;
            }
            divisor /= 2;
        }

        return representation;
    }

    /**
     * return string representation of zeros and ones for given byte
     * 
     * @param representation
     *            boolean array of byte
     * @return string representation of zeros and ones for given byte
     */
    public static String toStringRepresentation(boolean[] representation) {
        String nbyte = "";

        for (boolean bit : representation) {
            if (bit) {
                nbyte = "1" + nbyte;
            } else {
                nbyte = "0" + nbyte;
            }
        }

        return nbyte;
    }

    /**
     * return byte number (0-255) of given representation
     * 
     * @param representation
     *            boolean array of byte
     * @return byte number (0-255)
     */
    public static int processRepresentation(boolean[] representation) {
        int nbyte = 0;

        int partOfByte = 1;

        for (boolean bit : representation) {
            if (bit) {
                nbyte += partOfByte;
            }
            partOfByte *= 2;
        }

        return nbyte;
    }

    // unit test
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out
                .println("byte \ttoStringRepresentation()\tprocessRepresentation()");
        for (int i = 1; i <= 255; i = i * 2) {
            System.out
                    .println(i + "\t"
                            + toStringRepresentation(returnRepresentation(i))
                            + "\t\t\t"
                            + processRepresentation(returnRepresentation(i)));
        }
    }
}
