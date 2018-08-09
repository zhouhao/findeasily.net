package net.findeasily.website.service.impl;

import net.findeasily.website.domain.Listing;
import net.findeasily.website.mapper.ListingMapper;
import net.findeasily.website.service.ListingService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-08-08
 */
@Service
public class ListingServiceImpl extends ServiceImpl<ListingMapper, Listing> implements ListingService {

}
