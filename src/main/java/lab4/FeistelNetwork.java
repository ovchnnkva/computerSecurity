package lab4;

import java.util.Arrays;
import java.util.Random;

/**
 * В алгоритме шифрования Люцифер на основе сети Фейстеля применяются s-блоки и сдвиг.
 * Когда левый блок проходит через s-блок,
 * то значение левого блока складывается по модулю 2(xor) со значением s-блока.
 * Полученный результат складывается по модулю 2 с ключом.
 * Результат смещается на 11. Затем полученный резальтат складывается по модулю 2 с правым блоком и сохраняется в него.
 * Спустя 16 таких раундов, левый блок записывается в правый, а правый - в левый
 */
public class FeistelNetwork {

        // раундов шифрования (16)
        public static final int ROUNDS = 16;
        // разрядность блока данных для криптографии, менять нельзя т.к. определяет
        // тип int функции фейстеля
        public static final int DATA_BLOCK_WIDE = 32;
        // разрядность S-блока (4)
        public static final int S_BLOCK_WIDE = 4;
        public static final int MAGIC_ROTATE = 11;
        // разрядность ключа шифрования (128)
        public static final int KEY_SIZE = ROUNDS * DATA_BLOCK_WIDE / S_BLOCK_WIDE;
        // количество S-блоков в раунде (16)
        public static final int S_BLOCKS = 2 * DATA_BLOCK_WIDE / S_BLOCK_WIDE;
        // блоки сети фейстеля
        static int[][][] s = new int[ROUNDS][S_BLOCKS][(int) Math.pow(2, S_BLOCK_WIDE)]; // 16,16,16
        static void generate(int studentNum) {
            Random rand = new Random(studentNum);
            for (int r = 0; r < s.length; r++) {
                System.out.printf("ROUND: %d\n", r);
                for (int i = 0; i < s[r].length; i++) {
                    System.out.print(" {");
                        for (int j = 0; j < s[r][i].length; j++) {
                            s[r][i][j] = rand.nextInt(s[r][i].length);
                            System.out.print(s[r][i][j] + ",");
                        }
                        System.out.println("},");
                }
            }
        }
        static int str2int(String s) {
            int rez = 0;
            for (int i = 0; i < 4; i++) {
                rez |= (s.charAt(i) & 255) << (i * 8);
            }
            return rez;
        }
        static String int2str(int l) {
            StringBuilder rez = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                rez.append((char) (l & 255));
                l >>= 8;
            }
            return rez.toString();
        }
       public String crypt(String message,String key) {
           String leftBlock = message.substring(0, message.length()/2);
           String rightBlock = message.substring(message.length()/2, message.length());


           for(int i =0; i < ROUNDS; i++) {
               int sBlock = getSBlock(leftBlock, i);
               sBlock += str2int(key);
               sBlock = Integer.rotateLeft(sBlock,11);
               leftBlock = int2str(sBlock);

               rightBlock = int2str(str2int(rightBlock) ^ sBlock);
           }

           String temp = leftBlock;
           leftBlock = rightBlock;
           rightBlock = temp;
           return leftBlock + rightBlock;
       }
       public String decrypt(String message,String key) {
           String rightBlock = message.substring(0, message.length()/2);
           String leftBlock = message.substring(message.length()/2, message.length());

           for(int i = ROUNDS - 1; i >= 0; i--) {
               rightBlock = int2str(str2int(rightBlock) ^ str2int(leftBlock));
               leftBlock =  int2str(Integer.rotateRight(str2int(leftBlock) , 11));
               leftBlock = int2str(str2int(leftBlock) - str2int(key));
               leftBlock = int2str(getSBlock(leftBlock, i));
           }

           return leftBlock + rightBlock;
       }

       private int getSBlock(String leftBlock, int round) {
            int sBlock = Arrays.stream(s[round][round]).sum();
            return str2int(leftBlock) ^ sBlock;
       }
}
