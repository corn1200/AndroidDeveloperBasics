package com.example.androiddeveloperbasics;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }

    public void update(Word word) {
        new UpdateWordAsyncTask(mWordDao).execute(word);
    }

    public void deleteAll() {
        new DeleteAllWordsAsyncTask(mWordDao).execute();
    }

    public void deleteWord(Word word) {
        new DeleteWordAsyncTask(mWordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        InsertAsyncTask(WordDao mWordDao) {
            mAsyncTaskDao = mWordDao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    private static class UpdateWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        UpdateWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.update(words[0]);
            return null;
        }
    }

    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao mAsyncTaskDao;

        DeleteAllWordsAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class DeleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        DeleteWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.deleteWord(words[0]);
            return null;
        }
    }
}
