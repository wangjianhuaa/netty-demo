package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 13:43
 */
public class Constants {

    public static class FileStatus{

        /**
         * 文件状态 0 开始
         */
        public static int BEGIN = 0;

        /**
         * 文件状态 1 中间
         */
        public static int CENTER = 1;

        /**
         * 文件状态 2 结尾
         */
        public static int END = 2;

        /**
         * 文件状态 3 完成
         */
        public static int COMPLETE = 3;
    }

    public static class TransferType{

        /**
         * 传输文件请求 0
         */
        public static int REQUEST = 0;

        /**
         * 传输文件指令 1
         */
        public static int INSTRUCT = 1;

        /**
         * 传输文件数据 2
         */
        public static int DATA = 2;
    }
}
