package com.example.core.models;


import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {

    protected static final String RESOURCE_TYPE = "mysite/components/header";
    private static final int HOMEPAGE_LEVEL = 4; // adjust if needed

    @SlingObject
    private ResourceResolver resourceResolver;

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue
    private String logoPath;

    @ValueMapValue
    private String logoUrl;

    @ValueMapValue
    private String joinLabel;

    @ValueMapValue
    private String joinLink;

    @ValueMapValue
    private String worldwideLabel;

    @ValueMapValue
    private String worldwideLink;

    private List<NavigationItem> navItems = new ArrayList<>();

    @PostConstruct
    protected void init() {
        if (currentPage != null && !currentPage.getPath().contains("experience-fragment")) {
            // Get the "home" page
            Page homePage = currentPage.getAbsoluteParent(4);
            // ^ Adjust this number so that it points exactly to "home" in your tree

            if (homePage != null) {
                navItems = buildNav(homePage); // This will now pull About, Contact, Carrier
            }
        }
    }

    private List<NavigationItem> buildNav(Page rootPage) {
        List<NavigationItem> items = new ArrayList<>();
        Iterator<Page> children = rootPage.listChildren();

        while (children.hasNext()) {
            Page topLevel = children.next();
            NavigationItem item = new NavigationItem(topLevel);

            // Add dropdown items (child pages)
            Iterator<Page> subPages = topLevel.listChildren();
            while (subPages.hasNext()) {
                Page sub = subPages.next();
                item.getChildren().add(new NavigationItem(sub));
            }

            items.add(item);
        }
        return items;
    }
    public List<NavigationItem> getNavItems() {
        return navItems;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getLogoUrl(){
        return logoUrl;
    }

    public String getJoinLabel() {
        return joinLabel;
    }

    public String getJoinLink() {
        return joinLink;
    }

    public String getWorldwideLabel() {
        return worldwideLabel;
    }

    public String getWorldwideLink() {
        return worldwideLink;
    }

    public static class NavigationItem {
        private final String title;
        private final String path;
        private final List<NavigationItem> children = new ArrayList<>();

        public NavigationItem(Page page) {
            this.title = page.getTitle();
            this.path = page.getPath() + ".html";
        }

        public String getTitle() { return title; }
        public String getPath() { return path; }
        public List<NavigationItem> getChildren() { return children; }
    }
}