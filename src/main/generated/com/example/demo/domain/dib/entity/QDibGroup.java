package com.example.demo.domain.dib.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDibGroup is a Querydsl query type for DibGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDibGroup extends EntityPathBase<DibGroup> {

    private static final long serialVersionUID = 865093932L;

    public static final QDibGroup dibGroup = new QDibGroup("dibGroup");

    public final NumberPath<Long> dibGroupId = createNumber("dibGroupId", Long.class);

    public final StringPath dibName = createString("dibName");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QDibGroup(String variable) {
        super(DibGroup.class, forVariable(variable));
    }

    public QDibGroup(Path<? extends DibGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDibGroup(PathMetadata metadata) {
        super(DibGroup.class, metadata);
    }

}

