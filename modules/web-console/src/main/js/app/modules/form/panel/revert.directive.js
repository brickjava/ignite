/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

const template = '<i ng-show="form.$dirty" class="fa fa-undo pull-right" ng-click="revert($event)"></i>';

export default ['igniteFormRevert', ['$tooltip', '$table', ($tooltip, $table) => {
    const link = (scope, $element, $attrs, [form]) => {
        $tooltip($element, { title: 'Undo unsaved changes' });

        scope.form = form;

        scope.revert = (e) => {
            e.stopPropagation();

            $table.tableReset();

            _.forOwn(form.$defaults, (value, name) => {
                const field = form[name];

                if (field) {
                    field.$setViewValue(value);
                    field.$setPristine();
                    field.$render();
                }
            });

            form.$setPristine();
        };
    };

    return {
        restrict: 'E',
        scope: { },
        template,
        link,
        replace: true,
        require: ['^form']
    };
}]];
