package com.lettuce.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Code is far away from bug with the animal protected
 * 　┏┓　　  ┏┓
 * ┏┻┻━━━┻┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┣┛
 * 　　┗┳┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 * 文件yml配置
 *
 * @author Hosmos
 * @date 2021年08月27日
 */
@Configuration
@ConfigurationProperties(prefix = "parameters")
public class YmlConfig {
    private String status;
    private String domain;
    private String port;
    private String dns;
    private String productsShownType;
    private String mallType;
    private String productsShownPort;
    private String mallPort;
    private FileConfigs file;

    public static class FileConfigs {
        private String filePath;
        private String carousel;
        private String goodCover;
        private String good;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getCarousel() {
            return carousel;
        }

        public void setCarousel(String carousel) {
            this.carousel = carousel;
        }

        public String getGoodCover() {
            return goodCover;
        }

        public void setGoodCover(String goodCover) {
            this.goodCover = goodCover;
        }

        public String getGood() {
            return good;
        }

        public void setGood(String good) {
            this.good = good;
        }
    }

    public FileConfigs getFile() {
        return file;
    }

    public void setFile(FileConfigs file) {
        this.file = file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProductsShownPort() {
        return productsShownPort;
    }

    public void setProductsShownPort(String productsShownPort) {
        this.productsShownPort = productsShownPort;
    }

    public String getMallPort() {
        return mallPort;
    }

    public void setMallPort(String mallPort) {
        this.mallPort = mallPort;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getProductsShownType() {
        return productsShownType;
    }

    public void setProductsShownType(String productsShownType) {
        this.productsShownType = productsShownType;
    }

    public String getMallType() {
        return mallType;
    }

    public void setMallType(String mallType) {
        this.mallType = mallType;
    }
}
