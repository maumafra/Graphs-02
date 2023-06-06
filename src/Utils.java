//Alunos: Maurício Mafra Monnerat, Mauro Fialho
public class Utils {
    private static String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K",
        "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public static int indexOfCharacter(String character) {
        int count = 0;
        if (!character.substring(1).isEmpty()) {
            count = Integer.parseInt(character.substring(1));
        }
        for (int i = 0; i < alphabet.length; i++){
            if (character.toUpperCase().equals(alphabet[i])){
                return i + (count*26);
            }
        }
        return -1;
    }

    public static String getCharacterAt(int index) {
        int count = 0;
        int characterIndex = index;
        while (characterIndex > 26) {
            characterIndex = characterIndex%26;
            count++;
        }
        if (count > 0) {
            return alphabet[characterIndex]+count;
        }
        return alphabet[characterIndex];
    }

    public static String[][] convertStringToMatrix(String matrixText, int rows, int columns){
        String[][] matrix = new String[rows][columns];
        String formattedText = matrixText.replaceAll("\\s","");
        int count = 0;
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                matrix[row][column] = String.valueOf(formattedText.charAt(count));
                count++;
            }
        }
        return matrix;
    }

    public static int[] getStartEndIndexes(String values) {
        String[] formattedText = values.split("\\s");
        int[] startEndIndexes = new int[2];
        startEndIndexes[0] = indexOfCharacter(formattedText[0]);
        startEndIndexes[1] = indexOfCharacter(formattedText[1]);
        return startEndIndexes;
    }

    public static String[] getTwoValuesFromInput(String values) {
        return values.split("\\s");
    }
}
