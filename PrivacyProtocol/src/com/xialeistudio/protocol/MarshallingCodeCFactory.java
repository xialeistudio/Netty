package com.xialeistudio.protocol;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/2
 */
public class MarshallingCodeCFactory {
    public static NettyMarshallingDecoder buildMarshallingDecoder() {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);

        return new NettyMarshallingDecoder(provider, 1024);
    }

    public static NettyMarshallingEncoder buildMarshallingEncoder() {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        return new NettyMarshallingEncoder(provider);
    }
}
