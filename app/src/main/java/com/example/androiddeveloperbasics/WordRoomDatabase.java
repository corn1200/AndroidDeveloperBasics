package com.example.androiddeveloperbasics;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "wod_database")
                            // 마이그레이션 개체가 없는 경우 마이그레이션하는 대신 지우고 다시 작성
                            // 이 부분에서 마이그레이션은 실용적이지 않습니다.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    // 백그라운드에서 데이터베이스를 채운다
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final WordDao mDao;
        String[] words = {"dolphin", "crocodile", "cobra"};

        public PopulateDbAsync(WordRoomDatabase instance) {
            mDao = instance.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // 매번 깨끗한 데이터베이스로 앱을 시작함
            // 데이터베이스가 처음 생성될 때만 채운다면 필요하지 않습니다.
//            mDao.deleteAll();

//            만약 word 데이터를 가지고 있지 않다면, 초기 단어의 목록을 만듭니다.
            if (mDao.getAnyWord().length < 1) {
                for (int i = 0; i <= words.length - 1; i++) {
                    Word word = new Word(words[i]);
                    mDao.insert(word);
                }
            }
            return null;
        }
    }
}
