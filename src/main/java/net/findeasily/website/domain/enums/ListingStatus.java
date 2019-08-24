package net.findeasily.website.domain.enums;

public enum ListingStatus {
    // listing is created but still in draft status, not published
    DRAFT,
    // listing is published by owner, and it can be viewed by public users now
    PUBLISHED,
    // logic deleted, public users and owner cannot see it anymore (if do physical deletion, it may have FK issues/errors)
    DELETED,
    // listing is made offline by owner, owner can still view/update it, but public users will not be able to view it unless owner republishes it
    OFFLINE,
    // abusers may create fake listings, site admin can mark it as FAKE, so it will be available anymore
    FAKE
}
