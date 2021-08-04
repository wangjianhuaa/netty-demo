import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 15:30
 */
public class ApiTest {


    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            //换行符
                            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            //String 编码解码器
                            channel.pipeline().addLast(new StringDecoder(Charset.forName("GBk")));
                            channel.pipeline().addLast(new StringEncoder(Charset.forName("GBk")));
                            //自己实现的接收数据方法
                            channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    //接受msg信息
                                    System.out.println(new SimpleDateFormat("yyyy-MM-dd")
                                            .format(new Date())+"客户端接收到消息"+msg);
                                }


                            });
                        }

                    });

            ChannelFuture f = b.connect("127.0.0.1", 7397).sync();
            System.out.println("netty client demo start done.");
            //向服务端发送消息
            f.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我的结尾是一个换行符，用于传输半包粘包处理\r\n");
            f.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我的结尾是一个换行符，用于传输半包粘包处理\n");
            f.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我的结尾是一个换行符，用于传输半包粘包处理\n");
            f.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我的结尾是一个换行符，用于传输半包粘包处理\n");
            f.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我的结尾是一个换行符，用于传输半包粘包处理\n");

            f.channel().closeFuture().syncUninterruptibly();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            group.shutdownGracefully();
        }


    }
}
