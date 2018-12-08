package net.findeasily.website.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.findeasily.website.domain.Listing;
import net.findeasily.website.mapper.ListingMapper;
import net.findeasily.website.service.ListingService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-08-08
 */
@Service
public class ListingServiceImpl extends ServiceImpl<ListingMapper, Listing> implements ListingService {

}
