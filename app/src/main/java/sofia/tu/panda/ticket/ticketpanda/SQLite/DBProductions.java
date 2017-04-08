package sofia.tu.panda.ticket.ticketpanda.SQLite;

import sofia.tu.panda.ticket.ticketpanda.R;

public class DBProductions {

    private static String description1 = "\"Приказка за радостта да гледаш нагоре, към небето, да виждаш цветовете, осветени от слънцето… с очите на дете.\n" +
            "Приказка за доброто, което понякога боли.\"\n" +
            "Василена Радева  \n" +
            "\n" +
            "Представлението се препоръчва за деца над 6 годишна възраст.";
    private static Integer primaryImage1 = R.drawable.malkata_morska_sirena;
    private static Integer secondaryImage1 = R.drawable.malkata_morska_sirena_2;
    private static Integer thirdImage1 = R.drawable.malkata_morska_sirena_3;
    public static final String production1 = "INSERT INTO " + DBConstants.PROGRAM_TABLE +
            " (title, smallDescription, fullDescription, price, author, actors, director, production, primaryImage, secondaryImage, thirdImage) " +
            "VALUES('МАЛКАТА МОРСКА СИРЕНА', 'Приказка за радостта да гледаш нагоре, към небето…', " +
            "'" + description1 + "', '6', 'Катрин Ан по приказката', " +
            "'Симона Халачева, Юлиян Малинов, София Маринкова, Петя Силянова, Невена Калудова, Сава Пиперов, Росен Белов', 'Василена Радева', " +
            "'01.05.2017', '" + primaryImage1 + "', '" + secondaryImage1 + "', '" + thirdImage1 + "');";


    private static String description2 = "\"Възрастните никога нищо не разбират сами, а за децата е уморително все да обясняват\"\n" +
            "/А.Екзюпери/\n" +
            "\n" +
            "Наричат я \"тази непослушна писателка\"… \n" +
            "Астрид Линдгрен създава нов вид литература за деца. Със своите независими и неконвенционални герои тя разбива клишетата в детската литература.\n" +
            "\n" +
            "Спектакълът \"Роня, дъщерята на разбойника\" изрича тревогата си от разминаването между деца и родители. Роня и Бирк често предизвикват недоумение и смущение сред възрастните. Двамата са находчиви и смели, с много дух, решителност и твърдост. Децата са безпогрешни в чувството си за нравственост, в усета си за добродетели – откритост, състрадателност, доброта, в стремежа си към справедливост. Те показват какво е грешно в света по мирен начин. Не сочат с пръст като възрастните, а единствено чрез действията си се опитват да им покажат КАК ДА ЖИВЕЯТ В ЛЮБОВ ЕДИН КЪМ ДРУГ. Спонтанните им и смели реакции променят света.\n" +
            "Катя Петрова\n" +
            "Посвещавам на Дафи \n" +
            "\n" +
            "Препоръчва се за деца между 6 и 12 годишна възраст.\n" +
            "Интересно и вълнуващо и за възрастните.\n";
    private static Integer primaryImage2 = R.drawable.ronq_dashterqta_na_razboinika;
    private static Integer secondaryImage2 = R.drawable.ronq_dashterqta_na_razboinika2;
    private static Integer thirdImage2 = R.drawable.ronq_dashterqta_na_razboinika3;
    public static final String production2 = "INSERT INTO " + DBConstants.PROGRAM_TABLE +
            " (title, smallDescription, fullDescription, price, author, actors, director, production, primaryImage, secondaryImage, thirdImage) " +
            "VALUES('РОНЯ, ДЪЩЕРЯТА НА РАЗБОЙНИКА', 'Възрастните никога нищо не разбират сами, а за децата е уморително все да обясняват.', " +
            "'" + description2 + "', '8', 'по мотиви на Астрид Линдгрен, адаптация Катя Петрова', 'Николай Върбанов, Мила Банчева, Юлиян Рачков, Симона Халачева, Пламен Манасиев, Антон Григоров, Николай Димитров', " +
            "'Катя Петрова', '03.05.2017', '" + primaryImage2 + "', '" + secondaryImage2 + "', + '" + thirdImage2 + "');";

    private static String description3 = "Мери Попинз - трогателната, забавна и вълнуваща история на семейство Банкс, която и до днес ни кара да се смеем с най-странната гувернантка Мери Попинз. Тя ще ни отведе на магическо приключение, което може да ни направи малко по-добри и да ни накара да се замислим за силата на обичта и семейството. Само трябва да повярваме в нея, Мери Попинз, защото тя никога не лъже и знае всичко за нас... Уроците са както за децата, така и за техните родители. Мечтите и фантазиите на детското въображение са душата на постановката.\n" +
            "Анастасия Събева - режисьор";
    private static Integer primaryImage3 = R.drawable.meri_popinz;
    private static Integer secondaryImage3 = R.drawable.meri_popinz2;
    private static Integer thirdImage3 = R.drawable.meri_popinz3;
    public static final String production3 = "INSERT INTO " + DBConstants.PROGRAM_TABLE +
            " (title, smallDescription, fullDescription, price, author, actors, director, production, primaryImage, secondaryImage, thirdImage) " +
            "VALUES('МЕРИ ПОПИНЗ', 'Мери Попинз - трогателната, забавна и вълнуваща история на семейство Банкс...', " +
            "'" + description3 + "', '8', 'по мотиви от произведения на Памела Травърз', 'Мила Банчева, Юлиян Рачков, Йорданка Любенова, Илия Виделинов', " +
            "'Анастасия Събева', '08.05.2017', '" + primaryImage3 + "', '" + secondaryImage3 + "', '" + thirdImage3 + "');";

    private static String description4 = "За първи път в България се представя мюзикъла „Питър Пан“ от известния британски композитор Пиърс Чатър Робинсън по известната творба на Джеймс Матю Бари.\n" +
            "\n" +
            "Елате в чудния свят на Питър Пан – момчето от приказната страна Невърленд, което отказва да порасне. То кръстосва шпага с пиратите на злия капитан Хук, изживява лудата тръпка от летенето и магията на първата целувка.\n" +
            "Мюзикълът на Театър „София“ завладява с красиви костюми, романтични дуели на пиратски кораб, мелодични песни и великолепни актьорски изпълнения.\n" +
            "\n" +
            "С участието на студенти от Театрален Колеж „Любен Гройс” (Александър Валериев, Валери Георгиев, Йово Панайотов, Николай Йорданов)\n" +
            "\n" +
            "Продължителност: 90 минути";
    private static Integer primaryImage4 = R.drawable.pitar_pan;
    private static Integer secondaryImage4 = R.drawable.pitar_pan2;
    private static Integer thirdImage4 = R.drawable.pitar_pan2;
    public static final String production4 = "INSERT INTO " + DBConstants.PROGRAM_TABLE +
            " (title, smallDescription, fullDescription, price, author, actors, director, production, primaryImage, secondaryImage, thirdImage) " +
            "VALUES('ПИТЪР ПАН', 'Елате в чудния свят на Питър Пан – момчето от приказната страна Невърленд...', " +
            "'" + description4 + "', '10', 'Пиърс Чатър Робинсън', 'Антон Григоров, Лора Мутишева, Мартин Гяуров, Николай Върбанов, Николай Димитров, Росен Белов, Симона Халачева, София Маринкова, Юлиян Малинов, Юлиян Рачков', " +
            "'Бисерка Колевска', '02.05.2017', '" + primaryImage4 + "', '" + secondaryImage4 + "', '" + thirdImage4 + "');";


}
