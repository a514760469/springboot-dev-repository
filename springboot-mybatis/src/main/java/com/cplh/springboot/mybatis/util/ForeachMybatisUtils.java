package com.cplh.springboot.mybatis.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;

import java.util.Set;

public class ForeachMybatisUtils {

    private ForeachMybatisUtils() {
    }

    public static <C> ParamObject<C> createParamObject(C obj) {
        ParamObject<C> paramObject = new ParamObject<C>();
        return paramObject.setObj(obj);
    }

    public static <C> StringBuilder foreach(ParamObject<C> paramObject) {
        return foreach(paramObject, null);
    }

    @SuppressWarnings("rawtypes")
    public static <C> StringBuilder foreach(ParamObject<C> paramObject, Interceptor interceptor) {
        return foreach(paramObject.getObj(), paramObject.getCollection(), paramObject.getItem(), paramObject.getIndex(),
                paramObject.getItemFormatter(), paramObject.getSeparator(), paramObject.getOpen(), paramObject.getClose(), interceptor);
    }

    /**
     * itemFormatter部分用法：#{item,jdbcType=VARCHAR},#{item.3345,jdbcType=VARCHAR}其中3345为map的key, ${item}, ${item['3345']}其中3345为map的key
     *
     * @param <C>           List.class、Map.class、Array
     * @param obj           list、map、数组对象
     * @param collection    对应xml foreach标签的collection属性
     * @param item          对应xml foreach标签的item属性
     * @param index         对应xml foreach标签的index属性，但是在itemFormatter中只匹配 ${ } 格式
     * @param itemFormatter 对应xml foreach标签内 #{item}
     * @param separator     对应xml foreach标签的separator属性
     * @param open          对应xml foreach标签的open属性
     * @param close         对应xml foreach标签的close属性
     * @return 拼接后的动态sql
     */
    public static <C> StringBuilder foreach(C obj, String collection, String item, String itemFormatter,
                                            String separator, String open, String close) {
        return foreach(obj, collection, item, null, itemFormatter, separator, open, close, null);
    }

    public static <C> StringBuilder foreach(C obj, String collection, String item, String index, String itemFormatter,
                                            String separator, String open, String close) {
        return foreach(obj, collection, item, index, itemFormatter, separator, open, close, null);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <C> StringBuilder foreach(C obj, String collection, String item, String index, String itemFormatter, String separator,
                                            String open, String close, ForeachMybatisUtils.Interceptor interceptor) {
        if (obj == null) {
            throw new NullPointerException("object cannot be null");
        }
        if (collection == null || collection.trim().equals("")) {
            throw new NullPointerException("collection cannot be blank");
        }
        if (item == null || item.trim().isEmpty()) {
            throw new NullPointerException("item cannot be blank");
        }
        if (itemFormatter == null) {
            throw new NullPointerException("itemFormatter cannot be null, and you can fill #{item},please");
        }
        collection = collection.trim();
        item = item.trim();
        if (index != null && item.equals(index.trim())) {
            throw new IllegalArgumentException("index cannot be equal to item");
        }
        Pattern itemDynamicPattern = Pattern.compile("(?<=#\\{)" + item + "(?=[\\s\\S]*\\})");
        Pattern itemBindingPattern = Pattern.compile("\\$\\{" + item + "(?:(?:\\.|\\[)\\S+)?\\s*\\}");
        Pattern indexStaticPattern = null;
        if (index != null && !index.trim().isEmpty() && itemFormatter.contains("${")) {
            indexStaticPattern = Pattern.compile("\\$\\{" + index.trim() + "\\s*\\}");
        }
        if (separator == null) {
            separator = "";
        }

        StringBuilder sqlBuilder = new StringBuilder();
        if (open != null) {
            sqlBuilder.append(open);
        }
        String prod = "";
        int n = 0;
        try {
            if (obj instanceof Map) {
                Set<Entry> set = ((Map) obj).entrySet();
                for (Entry entry : set) {
                    String key = (String) entry.getKey();
                    if (interceptor != null && interceptor.preBreakIntercept(obj, key, entry.getValue())) {
                        break;
                    }
                    if (interceptor != null && interceptor.continueIntercept(obj, key, entry.getValue())) {
                        continue;
                    }
                    if (key.contains(".") || key.contains(" ")) {
                        throw new IllegalStateException("the Key of map can not contains '.' or ' '");
                    }
                    if (n > 0) {
                        sqlBuilder.append(separator);
                    }
                    prod = replaceAll(itemDynamicPattern, itemFormatter, collection + "." + key);
                    if (indexStaticPattern != null) {
                        prod = replaceAll(indexStaticPattern, prod, key);
                    }
                    prod = replaceBindingMap(itemBindingPattern, item, prod, key, obj);
                    sqlBuilder.append(prod);
                    n++;
                    if (interceptor != null && interceptor.postBreakIntercept(obj, key, entry.getValue())) {
                        break;
                    }
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                for (int i = 0, size = list.size(); i < size; i++) {
                    if (interceptor != null && interceptor.preBreakIntercept(obj, i, list.get(i))) {
                        break;
                    }
                    if (interceptor != null && interceptor.continueIntercept(obj, i, list.get(i))) {
                        continue;
                    }
                    if (n > 0) {
                        sqlBuilder.append(separator);
                    }
                    prod = replaceAll(itemDynamicPattern, itemFormatter, collection + "[" + i + "]");
                    if (indexStaticPattern != null) {
                        prod = replaceAll(indexStaticPattern, prod, "" + i);
                    }
                    prod = replaceBindingList(itemBindingPattern, item, prod, "" + i, obj);
                    sqlBuilder.append(prod);
                    n++;
                    if (interceptor != null && interceptor.postBreakIntercept(obj, i, list.get(i))) {
                        break;
                    }
                }
            } else if (obj.getClass().isArray()) {
                List list = Arrays.asList((Object[]) obj);
                for (int i = 0, size = list.size(); i < size; i++) {
                    if (interceptor != null && interceptor.preBreakIntercept(obj, i, list.get(i))) {
                        break;
                    }
                    if (interceptor != null && interceptor.continueIntercept(obj, i, list.get(i))) {
                        continue;
                    }
                    if (n > 0) {
                        sqlBuilder.append(separator);
                    }
                    prod = replaceAll(itemDynamicPattern, itemFormatter, collection + "[" + i + "]");
                    if (indexStaticPattern != null) {
                        prod = replaceAll(indexStaticPattern, prod, "" + i);
                    }
                    prod = replaceBindingList(itemBindingPattern, item, prod, "" + i, obj);
                    sqlBuilder.append(prod);
                    n++;
                    if (interceptor != null && interceptor.postBreakIntercept(obj, i, list.get(i))) {
                        break;
                    }
                }
            } else {
                throw new IllegalArgumentException("the Type of collection support only Array,List,Map");
            }
        } catch (OgnlException e) {
            throw new BindingException("ognl exception", e);
        }
        if (n < 1) {
            sqlBuilder.delete(0, sqlBuilder.length());
        } else {
            if (close != null) {
                sqlBuilder.append(close);
            }
        }
        return sqlBuilder;
    }

    public static interface Interceptor<C, K, T> {

        /**
         * for循环内是否执行break语句, break语句在循环内第一行
         *
         * @param collection 集合
         * @param item       集合元素
         * @param key        集合key或下标
         * @return 返回true, 则执行break语句
         */
        boolean preBreakIntercept(C collection, K key, T item);

        /**
         * for循环内是否执行break语句, break语句在循环内最后一行
         *
         * @param collection 集合
         * @param item       集合元素
         * @param key        集合key或下标
         * @return 返回true, 则执行break语句
         */
        boolean postBreakIntercept(C collection, K key, T item);

        /**
         * for循环内是否执行continue语句
         *
         * @param collection 集合
         * @param item       集合元素
         * @param key        集合key或下标
         * @return 返回true, 则执行continue语句
         */
        boolean continueIntercept(C collection, K key, T item);

    }

    private static String replaceAll(Pattern pattern, String itemFormatter, String collection) {
        itemFormatter = pattern.matcher(itemFormatter).replaceAll(collection);
        return itemFormatter;
    }

    private static <C> String replaceBindingMap(Pattern pattern, String item, String itemFormatter, String index, C obj) throws OgnlException {
        Matcher matcher = pattern.matcher(itemFormatter);
        StringBuffer buffer = new StringBuffer();
        matcher.reset();
        String group = "";
        while (matcher.find()) {
            group = matcher.group();
            group = group.replaceFirst("\\$\\{" + item, "#root['" + index + "']");
            group = group.substring(0, group.length() - 1).trim();
            group = String.valueOf(Ognl.getValue(group, obj));
            matcher.appendReplacement(buffer, group);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static <C> String replaceBindingList(Pattern pattern, String item, String itemFormatter, String index, C obj) throws OgnlException {
        Matcher matcher = pattern.matcher(itemFormatter);
        StringBuffer buffer = new StringBuffer();
        matcher.reset();
        String group = "";
        while (matcher.find()) {
            group = matcher.group();
            group = group.replaceFirst("\\$\\{" + item, "#root[" + index + "]");
            group = group.substring(0, group.length() - 1).trim();
            group = String.valueOf(Ognl.getValue(group, obj));
            matcher.appendReplacement(buffer, group);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public static class ParamObject<C> {
        private C obj;
        private String collection;
        private String item = "item";
        private String index;
        private String itemFormatter;
        private String separator;
        private String open;
        private String close;

        public C getObj() {
            return obj;
        }

        public ParamObject<C> setObj(C obj) {
            this.obj = obj;
            return this;
        }

        public String getCollection() {
            return collection;
        }

        public ParamObject<C> setCollection(String collection) {
            this.collection = collection;
            return this;
        }

        public String getItem() {
            return item;
        }

        public ParamObject<C> setItem(String item) {
            this.item = item;
            return this;
        }

        public String getIndex() {
            return index;
        }

        public ParamObject<C> setIndex(String index) {
            this.index = index;
            return this;
        }

        public String getItemFormatter() {
            return itemFormatter;
        }

        public ParamObject<C> setItemFormatter(String itemFormatter) {
            this.itemFormatter = itemFormatter;
            return this;
        }

        public String getSeparator() {
            return separator;
        }

        public ParamObject<C> setSeparator(String separator) {
            this.separator = separator;
            return this;
        }

        public String getOpen() {
            return open;
        }

        public ParamObject<C> setOpen(String open) {
            this.open = open;
            return this;
        }

        public String getClose() {
            return close;
        }

        public ParamObject<C> setClose(String close) {
            this.close = close;
            return this;
        }

        public StringBuilder foreach() {
            return this.foreach(null);
        }

        @SuppressWarnings("rawtypes")
        public StringBuilder foreach(Interceptor interceptor) {
            return ForeachMybatisUtils.foreach(this, interceptor);
        }

    }

    public interface InnerForeach<C, K> {
        CharSequence foreach(C innerObj, K index);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <C> StringBuilder nestedForeach(C obj, String separator, String open, String close, InnerForeach innerForeach) {
        if (obj == null) {
            throw new NullPointerException("object can not is null");
        }
        if (separator == null) {
            separator = "";
        }
        StringBuilder sqlBuilder = new StringBuilder();
        if (open != null) {
            sqlBuilder.append(open);
        }
        int n = 0;
        int i = 0;
        CharSequence sqlItem = null;
        if (obj instanceof Map) {
            Set<Entry> set = ((Map) obj).entrySet();
            for (Entry entry : set) {
                sqlItem = innerForeach.foreach(entry.getValue(), entry.getKey());
                if (sqlItem != null && !sqlItem.toString().trim().isEmpty()) {
                    if (n > 0) {
                        sqlBuilder.append(separator);
                    }
                    sqlBuilder.append(sqlItem);
                    n++;
                }
                i++;
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            for (Object element : list) {
                sqlItem = innerForeach.foreach(element, i);
                if (sqlItem != null && !sqlItem.toString().trim().isEmpty()) {
                    if (n > 0) {
                        sqlBuilder.append(separator);
                    }
                    sqlBuilder.append(sqlItem);
                    n++;
                }
                i++;
            }
        } else if (obj.getClass().isArray()) {
            List list = Arrays.asList((Object[]) obj);
            for (Object element : list) {
                sqlItem = innerForeach.foreach(element, i);
                if (sqlItem != null && !sqlItem.toString().trim().isEmpty()) {
                    if (n > 0) {
                        sqlBuilder.append(separator);
                    }
                    sqlBuilder.append(sqlItem);
                    n++;
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("the Type of collection support only Array,List,Map");
        }
        if (n < 1) {
            sqlBuilder.delete(0, sqlBuilder.length());
        } else {
            if (close != null) {
                sqlBuilder.append(close);
            }
        }
        return sqlBuilder;
    }

}