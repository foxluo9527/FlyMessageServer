package com.service.impl;

import com.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PostService")
@Transactional
public class PostServiceImpl implements PostService {

}
