/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.superchat.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.superchat.R
import com.example.superchat.core.designsystem.icon.AppIcons
import com.example.superchat.navigation.temp.BookmarksRoute
import com.example.superchat.navigation.temp.ForYouBaseRoute
import com.example.superchat.navigation.temp.ForYouRoute
import com.example.superchat.navigation.temp.InterestsRoute
import kotlin.reflect.KClass


/**
 * Type for the top level destinations in the application. Contains metadata about the destination
 * that is used in the top app bar and common navigation UI.
 *
 * @param selectedIcon The icon to be displayed in the navigation UI when this destination is
 * selected.
 * @param unselectedIcon The icon to be displayed in the navigation UI when this destination is
 * not selected.
 * @param iconTextId Text that to be displayed in the navigation UI.
 * @param titleTextId Text that is displayed on the top app bar.
 * @param route The route to use when navigating to this destination.
 * @param baseRoute The highest ancestor of this destination. Defaults to [route], meaning that
 * there is a single destination in that section of the app (no nested destinations).
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    FOR_YOU(
        selectedIcon = AppIcons.Upcoming,
        unselectedIcon = AppIcons.UpcomingBorder,
        iconTextId = R.string.app_name,
        titleTextId = R.string.app_name,
        route = ForYouRoute::class,
        baseRoute = ForYouBaseRoute::class,
    ),
    BOOKMARKS(
        selectedIcon = AppIcons.Bookmarks,
        unselectedIcon = AppIcons.BookmarksBorder,
        iconTextId = R.string.app_name,
        titleTextId = R.string.app_name,
        route = BookmarksRoute::class,
    ),
    INTERESTS(
        selectedIcon = AppIcons.Grid3x3,
        unselectedIcon = AppIcons.Grid3x3,
        iconTextId = R.string.app_name,
        titleTextId = R.string.app_name,
        route = InterestsRoute::class,
    ),
}