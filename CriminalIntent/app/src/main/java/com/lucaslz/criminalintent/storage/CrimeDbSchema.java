package com.lucaslz.criminalintent.storage;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/8.
 */
public class CrimeDbSchema {

    public static final class CrimeTable {

        public static final String NAME = "crimes_";

        public static final String TEMP_NAME = "crimes_temp_";

        public static final class Cols {
            public static final String UUID = "uuid_";
            public static final String TITLE = "title_";
            public static final String DATE = "date_";
            public static final String SOLVED = "solved_";
            public static final String SUSPECT = "suspect_";
        }
    }
}
