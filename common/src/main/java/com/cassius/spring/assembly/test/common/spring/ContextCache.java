package com.cassius.spring.assembly.test.common.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.style.ToStringCreator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ContextCache
 *
 * @author Cassius Cai
 * @version v 0.1 8/6/15 00:38 Exp $
 */
public class ContextCache {
    /**
     * Map of context keys to Spring {@code ApplicationContext} instances.
     */
    private final Map<MergedContextConfiguration, ApplicationContext> contextMap = new ConcurrentHashMap<MergedContextConfiguration, ApplicationContext>(
        64);

    /**
     * Map of parent keys to sets of children keys, representing a top-down <em>tree</em>
     * of context hierarchies. This information is used for determining which subtrees
     * need to be recursively removed and closed when removing a context that is a parent
     * of other contexts.
     */
    private final Map<MergedContextConfiguration, Set<MergedContextConfiguration>> hierarchyMap = new ConcurrentHashMap<MergedContextConfiguration, Set<MergedContextConfiguration>>(
        64);

    /**
     * The Hit count.
     */
    private final AtomicInteger hitCount = new AtomicInteger();

    /**
     * The Miss count.
     */
    private final AtomicInteger missCount = new AtomicInteger();

    /**
     * Clear all contexts from the cache and clear context hierarchy information as well.
     */
    public void clear() {
        this.contextMap.clear();
        this.hierarchyMap.clear();
    }

    /**
     * Clear hit and miss count statistics for the cache (i.e., reset counters to zero).
     */
    public void clearStatistics() {
        this.hitCount.set(0);
        this.missCount.set(0);
    }

    /**
     * Determine whether there is a cached context for the given key.
     * @param key the context key (never 
     *)
     * @return if the cache contains a context with the given key
     */
    public boolean contains(MergedContextConfiguration key) {
        Assert.notNull(key, "Key must not be null");
        return this.contextMap.containsKey(key);
    }

    /**
     * Obtain a cached {@code ApplicationContext} for the given key.
     * <p>The {@link #getHitCount() hit} and {@link #getMissCount() miss} counts will
     * be updated accordingly.
     * @param key the context key (never 
     *)
     * @return the corresponding 
     * instance, or 
     * if not found in the cache
     * @see #remove
     */
    public ApplicationContext get(MergedContextConfiguration key) {
        Assert.notNull(key, "Key must not be null");
        ApplicationContext context = this.contextMap.get(key);
        if (context == null) {
            this.missCount.incrementAndGet();
        } else {
            this.hitCount.incrementAndGet();
        }
        return context;
    }

    /**
     * Explicitly add an {@code ApplicationContext} instance to the cache under the given key.
     * @param key the context key (never
     *)
     * @param context the
     * instance (never
     *)
     */
    public void put(MergedContextConfiguration key, ApplicationContext context) {
        Assert.notNull(key, "Key must not be null");
        Assert.notNull(context, "ApplicationContext must not be null");

        this.contextMap.put(key, context);
        MergedContextConfiguration child = key;
        MergedContextConfiguration parent = child.getParent();
        while (parent != null) {
            Set<MergedContextConfiguration> list = this.hierarchyMap.get(parent);
            if (list == null) {
                list = new HashSet<MergedContextConfiguration>();
                this.hierarchyMap.put(parent, list);
            }
            list.add(child);
            child = parent;
            parent = child.getParent();
        }
    }

    /**
     * Remove the context with the given key from the cache and explicitly
     * {@linkplain ConfigurableApplicationContext#close() close} it if it is an
     * instance of {@link ConfigurableApplicationContext}.
     * <p>Generally speaking, you would only call this method if you change the
     * state of a singleton bean, potentially affecting future interaction with
     * the context.
     * <p>In addition, the semantics of the supplied {@code HierarchyMode} will
     * be honored. See the Javadoc for {@link DirtiesContext.HierarchyMode} for details.
     * @param key the context key; never
     * @param hierarchyMode the hierarchy mode; may be
     * if the context
     * is not part of a hierarchy
     */
    public void remove(MergedContextConfiguration key, DirtiesContext.HierarchyMode hierarchyMode) {
        Assert.notNull(key, "Key must not be null");

        // startKey is the level at which to begin clearing the cache, depending
        // on the configured hierarchy mode.
        MergedContextConfiguration startKey = key;
        if (hierarchyMode == DirtiesContext.HierarchyMode.EXHAUSTIVE) {
            while (startKey.getParent() != null) {
                startKey = startKey.getParent();
            }
        }

        List<MergedContextConfiguration> removedContexts = new ArrayList<MergedContextConfiguration>();
        remove(removedContexts, startKey);

        // Remove all remaining references to any removed contexts from the
        // hierarchy map.
        for (MergedContextConfiguration currentKey : removedContexts) {
            for (Set<MergedContextConfiguration> children : this.hierarchyMap.values()) {
                children.remove(currentKey);
            }
        }

        // Remove empty entries from the hierarchy map.
        for (MergedContextConfiguration currentKey : this.hierarchyMap.keySet()) {
            if (this.hierarchyMap.get(currentKey).isEmpty()) {
                this.hierarchyMap.remove(currentKey);
            }
        }
    }

    /**
     * Remove void.
     *
     * @param removedContexts the removed contexts
     * @param key the key
     */
    private void remove(List<MergedContextConfiguration> removedContexts,
                        MergedContextConfiguration key) {
        Assert.notNull(key, "Key must not be null");

        Set<MergedContextConfiguration> children = this.hierarchyMap.get(key);
        if (children != null) {
            for (MergedContextConfiguration child : children) {
                // Recurse through lower levels
                remove(removedContexts, child);
            }
            // Remove the set of children for the current context from the hierarchy map.
            this.hierarchyMap.remove(key);
        }

        // Physically remove and close leaf nodes first (i.e., on the way back up the
        // stack as opposed to prior to the recursive call).
        ApplicationContext context = this.contextMap.remove(key);
        if (context instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) context).close();
        }
        removedContexts.add(key);
    }

    /**
     * Generate a text string, which contains the {@linkplain #size} as well
     * as the {@linkplain #getHitCount() hit}, {@linkplain #getMissCount() miss},
     * and {@linkplain #getParentContextCount() parent context} counts.
     * @return the string
     */
    @Override
    public String toString() {
        return new ToStringCreator(this).append("size", size()).append("hitCount", getHitCount())
            .append("missCount", getMissCount())
            .append("parentContextCount", getParentContextCount()).toString();
    }

    /**
     * Get the overall hit count for this cache.
     * <p>A <em>hit</em> is an access to the cache, which returned a non-null context
     * for a queried key.
     * @return the hit count
     */
    public int getHitCount() {
        return this.hitCount.get();
    }

    /**
     * Get the overall miss count for this cache.
     * <p>A <em>miss</em> is an access to the cache, which returned a {@code null} context
     * for a queried key.
     * @return the miss count
     */
    public int getMissCount() {
        return this.missCount.get();
    }

    /**
     * Determine the number of contexts currently stored in the cache.
     * <p>If the cache contains more than {@code Integer.MAX_VALUE} elements,
     * this method returns {@code Integer.MAX_VALUE}.
     * @return the int
     */
    public int size() {
        return this.contextMap.size();
    }

    /**
     * Determine the number of parent contexts currently tracked within the cache.
     * @return the parent context count
     */
    public int getParentContextCount() {
        return this.hierarchyMap.size();
    }
}
