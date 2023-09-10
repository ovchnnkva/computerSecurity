package lab2.part1;

import lab2.part2.FrequencyCryptanalyzer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class Lab2p1Test {

    private Cryptoanalyzer cryptoanalyzer;

    private String testText1;

    private String testText2;

    private String testText3;

    private String testText4;

    private String testText5;

    private String testText6;

    private String testText7;

    private String testText8;

    private String testText9;

    private String testText10;

    @Before
    public void init() {
        cryptoanalyzer = new Cryptoanalyzer();

        testText1 = "блюншж явфвн, бйёпнёж яиэбёйёнляёф! оплизкриоь о еэбэфвж,\n" +
                "злабэ кэ яштлбв квжнлккэь овпщ блидкэ яшбэяэпщ кв плищзл зиэоо\n" +
                "(зиэооёсёзэуёь) ё кв мнлопл фёоил (нванвооёь). кэ яштлбв крдкл ялеянэцэпщ x,\n" +
                "y, width, height ё зиэоо люкэнрдвкклал кэ зэнпёкзв лючвзпэ (ёиё зллнбёкэпш\n" +
                "плфвз злкпрнля ъплал лючвзпэ, э-иь овайвкпэуёь).\n";

        testText2 = "эзъйфг эюжх. зйьщжвбщлзйф дмйкзы ийюэезавев люёф ийзюдлзы\n" +
                "из дмйкм ai, зжв ы щллщрю. цлз жюзъшбщлюехжфю люёф, ыф ёзаюлю\n" +
                "ыфъйщлх ечъмч кызч.\n";

        testText3 = "зрмцфмн ёпдзмрмфтёмы, зиса зтефян. ря х ёдрм еихизтёдпм ут\n" +
                "очфхч фтхд отедпац. утпстъисстн уфтжфдрря рси сднцм си чздптха,\n" +
                "пмьа цтпаот офдцомн упдс очфхд:\n";

        testText4 = "зтефян зиса. ёт ёпткисмм сдьи отррифыихоти уфизпткисми ут\n" +
                "утотумнстрч техпчкмёдсмв. д цдоки хумхто уфизтхцдёпгирящ сдрм чхпчж.\n" +
                "ечзир фдзя здпасиньирч уптзтцётфстрч хтцфчзсмыихцёч!\n";

        testText5 = "сьоюьт баюь. обсйат сьоюи этютшцыбай каьа внчщ ын ятюптю.\n" +
                "каь сщм ныыи п кщтшаюцшб ьэцяныцт ыьътышщнабюи ынсь хнамыбай.\n";


        testText6 = "упьлнэюняхюр, пшфюьфх! шрщк уъняю щлюлчзк эрьоррнщл. к\n" +
                "кнчкйэз ъафвфлчзщжш ыьрпэюлнфюрчршцьяыщъх ыьъфунъпэюнрщщъ-\n" +
                "чъофэюфгрэцъх цъшылщфф. улщфшлршэк ыъэюлнцлшфыъ йоя ьъээфф.\n";

        testText7 = "тэпяйш туьк. рэ юуярйд samsung ai юяэрэтчб тън ьо 10сэ ыоабуя\n" +
                "щъоаа р ысв.\n";

        testText8 = "ощлыёф мпвпы, очуэыуф мцкоучуыщмув! ъщоьхксуэп, хщнок ю\n" +
                "шкь люопэ ьцпоюидпп ткшйэуп?\n";

        testText9 = "ощлыёф опшж. мкг ъщвэщмёф йдух кхэумуыщмкцу.\n";

        testText10 = "чвфдоэ чшбп, жхуъушаоэ юяьшбё ! х гдвчвяъшбьш булшцв е хуаь\n" +
                "чьуявцу- бугдухятс булш гдшчявъшбьш гв гвчюяскшбьс ьбёшдбшёу чят хулшэ\n" +
                "вдцубьыуйьь!\n";

    }

    @Test
    public void task1Test() {
        cryptoanalyzer.decrypt(testText1);

    }

    @Test
    public void task2Test() {
        cryptoanalyzer.decrypt(testText2);
    }
    @Test
    public void task3Test() {
        cryptoanalyzer.decrypt(testText3);
    }
    @Test
    public void task4Test() {
        cryptoanalyzer.decrypt(testText4);
    }
    @Test
    public void task5Test() {
        cryptoanalyzer.broot(testText5);
    }
    @Test
    public void task6Test() {
        cryptoanalyzer.broot(testText6);
    }
    @Test
    public void task7Test() {
        cryptoanalyzer.decrypt(testText7);
    }
    @Test
    public void task8Test() {
        cryptoanalyzer.broot(testText8);
    }
    @Test
    public void task9Test() {
        cryptoanalyzer.broot(testText9);
    }
    @Test
    public void task10Test() {
        cryptoanalyzer.broot(testText10);
    }

}
