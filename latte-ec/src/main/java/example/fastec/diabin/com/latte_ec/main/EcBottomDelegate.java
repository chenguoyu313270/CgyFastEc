//package example.fastec.diabin.com.latte_ec.main;
//
//import android.graphics.Color;
//
//import com.flj.latte.delegates.bottom.BaseBottomDelegate;
//import com.flj.latte.delegates.bottom.BottomItemDelegate;
//import com.flj.latte.delegates.bottom.BottomTabBean;
//import com.flj.latte.delegates.bottom.ItemBuilder;
//
//import java.util.LinkedHashMap;
//
//import example.fastec.diabin.com.latte_ec.main.index.IndexDelegate;
//
///**
// * Created by Administrator on 2018\6\6 0006.
// */
//
//public class EcBottomDelegate extends BaseBottomDelegate {
//    @Override
//    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
//        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items=new LinkedHashMap<>();
//        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
//        items.put(new BottomTabBean("{fa-sort}", "分类"), new IndexDelegate());
//        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
//        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
//        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
//
//
//
//
//        return builder.addItems(items).build();
//    }
//
//    @Override
//    public int setInderxDelegate() {
//        return 0;
//    }
//
//    @Override
//    public int setClickedColor() {
//        return Color.parseColor("#ffff8800");
//    }
//}