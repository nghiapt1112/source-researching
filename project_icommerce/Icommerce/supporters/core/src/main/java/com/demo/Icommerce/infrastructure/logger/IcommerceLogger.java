package com.demo.Icommerce.infrastructure.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IcommerceLogger {
    // TODO:, FIXME:
    // 1 Instance of logger to share and prevent of creation redundant instances of loggers.
    // & to centrilize logger instance for future scale on monitoring/tracking/tracing
    public static final Logger logger = LoggerFactory.getLogger("[IcommerceLogger]");

}
