package net.te.mymuiltlistdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AppCompatTextView tvSend;
    SearchView searchView;
    ListView listView;

    private TreeAdapter adapter;
    private List<TreePoint> pointList = new ArrayList<>();
    private HashMap<String, TreePoint> pointMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transparentStatusBar();
        initView();
        init();
        addListener();


    }

    private void transparentStatusBar() {
        Window window = getWindow();
        View decorView = window.getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initView() {
        tvSend = findViewById(R.id.tv_send);
        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.list_view);
        findViewById(R.id.iv_back).setOnClickListener(this);

        //去下划线
        searchView.findViewById(androidx.appcompat.R.id.search_plate).setBackground(null);
        searchView.findViewById(androidx.appcompat.R.id.submit_area).setBackground(null);
        searchView.setQueryHint("搜索用电单位");
        //修改searchView 中hint字体大小(系统)
        TextView textView = searchView.findViewById(R.id.search_src_text);
        textView.setTextSize(14);//字体、提示字体大小
    }


    public void init() {
        adapter = new TreeAdapter(this, pointList, pointMap);
        listView.setAdapter(adapter);

        initPointData();
//        initPointListData();
    }

    private void initPointData() {
        pointList.clear();
        //ID是PARENT_ID的主键    IS_LEAF： 1叶子节点  0根节点  DISPLAY_ORDER ：同一个级别的显示顺序
        pointList.add(new TreePoint("1001", "浙江电务", "0", "0", 1));
        pointList.add(new TreePoint("1002", "奥尚娱乐", "0", "0", 2));
        pointList.add(new TreePoint("1003", "潮商KTV", "0", "0", 3));
        pointList.add(new TreePoint("1004", "第二农贸", "0", "0", 4));
        pointList.add(new TreePoint("1005", "丁兰电影城", "0", "0", 5));
        pointList.add(new TreePoint("1006", "丁桥世纪华联", "0", "0", 6));

        int parentIdJ = 1007;
        int parentIdK = 1013;
        int id = 1006;
        for (int i = 1; i < 5; i++) {
            if (i != 1) {
                parentIdJ += 126;
            }
            id++;
            pointList.add(new TreePoint("" + id, "配电房" + i, "" + "1001", "0", i));//id 1007  1025  parentId 1001
            id++;
            pointList.add(new TreePoint("" + id, "配电房" + i, "" + "1002", "0", i));//id 1008  parentId 1002
            id++;
            pointList.add(new TreePoint("" + id, "配电房" + i, "" + "1003", "0", i));//id 1009  parentId 1003
            id++;
            pointList.add(new TreePoint("" + id, "配电房" + i, "" + "1004", "0", i));//id 1010  parentId 1004
            id++;
            pointList.add(new TreePoint("" + id, "配电房" + i, "" + "1005", "0", i));//id 1011  parentId 1005
            id++;
            pointList.add(new TreePoint("" + id, "配电房" + i, "" + "1006", "0", i));//id 1012  parentId 1006
            for (int j = 1; j < 5; j++) {
                if (j != 1) {
                    parentIdK += 30;
                }
                id++;
                pointList.add(new TreePoint("" + id, "屏柜" + j, "" + parentIdJ, "0", j)); //id 1013  parentId   1007
                id++;
                pointList.add(new TreePoint("" + id, "屏柜" + j, "" + (parentIdJ + 1), "0", j));//1014             1008
                id++;
                pointList.add(new TreePoint("" + id, "屏柜" + j, "" + (parentIdJ + 2), "0", j));//1015             1009
                id++;
                pointList.add(new TreePoint("" + id, "屏柜" + j, "" + (parentIdJ + 3), "0", j));//1016             1010
                id++;
                pointList.add(new TreePoint("" + id, "屏柜" + j, "" + (parentIdJ + 4), "0", j));//1017             1011
                id++;
                pointList.add(new TreePoint("" + id, "屏柜" + j, "" + (parentIdJ + 5), "0", j));//1018             1012
                for (int k = 1; k < 5; k++) {
                    id++;
                    pointList.add(new TreePoint("" + id, "间隔" + k, "" + parentIdK, "1", k)); //id 1019  parentId   1013
                    id++;
                    pointList.add(new TreePoint("" + id, "间隔" + k, "" + (parentIdK + 1), "1", k));//1020             1014
                    id++;
                    pointList.add(new TreePoint("" + id, "间隔" + k, "" + (parentIdK + 2), "1", k));//1021             1015
                    id++;
                    pointList.add(new TreePoint("" + id, "间隔" + k, "" + (parentIdK + 3), "1", k));//1022             1016
                    id++;
                    pointList.add(new TreePoint("" + id, "间隔" + k, "" + (parentIdK + 4), "1", k));//1023             1017
                    id++;
                    pointList.add(new TreePoint("" + id, "间隔" + k, "" + (parentIdK + 5), "1", k));//1024             1018
                }
            }
            parentIdK += 36;
        }
        //打乱集合中的数据
        Collections.shuffle(pointList);
        //对集合中的数据重新排序
        updateData();
    }


    public void addListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.onItemClick(position);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TreePoint treePoint = null;
                for (TreePoint point : pointList) {
                    if (query != null && query.equals(point.getNNAME())) {
                        searchFlag = true;
                        treePoint = point;
                        break;
                    } else {
                        searchFlag = false;
                    }
                }
                if (searchFlag && treePoint != null) {
                } else {
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter(newText);
                if (newText.length() == 0) {
                }
                return false;
            }
        });

    }

    private void searchAdapter(String query) {
        adapter.setKeyword(query);
    }

    //对数据排序 深度优先
    private void updateData() {
        for (TreePoint treePoint : pointList) {
            pointMap.put(treePoint.getID(), treePoint);
        }
        Collections.sort(pointList, new Comparator<TreePoint>() {
            @Override
            public int compare(TreePoint lhs, TreePoint rhs) {
                int llevel = TreeUtils.getLevel(lhs, pointMap);
                int rlevel = TreeUtils.getLevel(rhs, pointMap);
                if (llevel == rlevel) {
                    if (lhs.getPARENTID().equals(rhs.getPARENTID())) {  //左边小
                        return lhs.getDISPLAY_ORDER() > rhs.getDISPLAY_ORDER() ? 1 : -1;
                    } else {  //如果父辈id不相等
                        //同一级别，不同父辈
                        TreePoint ltreePoint = TreeUtils.getTreePoint(lhs.getPARENTID(), pointMap);
                        TreePoint rtreePoint = TreeUtils.getTreePoint(rhs.getPARENTID(), pointMap);
                        return compare(ltreePoint, rtreePoint);  //父辈
                    }
                } else {  //不同级别
                    if (llevel > rlevel) {   //左边级别大       左边小
                        if (lhs.getPARENTID().equals(rhs.getID())) {
                            return 1;
                        } else {
                            TreePoint lreasonTreePoint = TreeUtils.getTreePoint(lhs.getPARENTID(), pointMap);
                            return compare(lreasonTreePoint, rhs);
                        }
                    } else {   //右边级别大   右边小
                        if (rhs.getPARENTID().equals(lhs.getID())) {
                            return -1;
                        }
                        TreePoint rreasonTreePoint = TreeUtils.getTreePoint(rhs.getPARENTID(), pointMap);
                        return compare(lhs, rreasonTreePoint);
                    }
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    private boolean searchFlag = false;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_send:
                for (TreePoint treePoint : pointList) {
                    if (treePoint.getNNAME().equals(searchView.getQuery().toString().trim())) {
                        searchFlag = true;
                    }
                }
                if (searchFlag) {

                } else {

                }

                break;
        }
    }

    /**
     * 和 setContentView 对应的方法
     */
    public ViewGroup getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }
    /**
     * 初始化软键盘
     */
    protected void initSoftKeyboard() {
        // 点击外部隐藏软键盘，提升用户体验
        getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
            }
        });
    }
    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        // 隐藏软键盘，避免软键盘引发的内存泄露
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
