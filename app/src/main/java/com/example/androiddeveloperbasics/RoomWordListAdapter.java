package com.example.androiddeveloperbasics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomWordListAdapter extends RecyclerView.Adapter<RoomWordListAdapter.WordViewHolder> {
    private final LayoutInflater mInflater;
    private List<Word> mWords; // 캐시된 Word 사본

    RoomWordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public Word getWordAtPosition(int position) {
        return mWords.get(position);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            // 데이터가 아직 준비되지 않은 경우를 다룹니다.
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() 는 여러번 호출되고, 처음 호출 될 경우,
    // mWords 는 업데이트 되지 않습니다.(처음에는 null 이며 null 값을 반환할 수 없음을 의미함)
    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        } else {
            return 0;
        }
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
